package com.farmbazaar.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmbazaar.dao.CartDao;
import com.farmbazaar.dao.CartItemDao;
import com.farmbazaar.dao.CustomerDao;
import com.farmbazaar.dao.OrderDao;
import com.farmbazaar.dao.ProductDao;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.dto.CartItemRequest;
import com.farmbazaar.dto.CheckoutRequest;
import com.farmbazaar.pojos.Cart;
import com.farmbazaar.pojos.CartItem;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Order;
import com.farmbazaar.pojos.Product;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
    private CartDao cartDao;
	
	@Autowired
    private CartItemDao cartItemDao;
	
	@Autowired
	private OrderDao orderDao;
	
	
//	@Autowired
//    private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public ApiResponse addNewCustomer(Customer transientCategory) {
		
		// Encrypt password before saving
	    //transientCategory.setPassword(passwordEncoder.encode(transientCategory.getPassword()));
	    
		// set ID null to avoid StaleObjectStateException
		transientCategory.setId(null);
		Customer persistentCategory = customerDao.save(transientCategory);
		return new ApiResponse("Added new catgroy with ID " + persistentCategory.getId());
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> productsList = productDao.findAll();
		// Encode image data to Base64 before sending
        productsList.forEach(Product::encodeImageDataToBase64);
		return productsList;
	}
	
	
	public String addToCart(int customerId, CartItemRequest cartItemRequest) {
		
		//fetch the customer from database
        Optional<Customer> optionalCustomer = customerDao.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return "Error: Customer not found";
        }
        Customer customer = optionalCustomer.get();

        // Check if the customer has a cart, & create a new one if not
        Cart cart = customer.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setCustomer(customer);
            customer.setCart(cart); // Associate cart with customer
        }
        
        
        // fetch the product from database
        Optional<Product> optionalProduct = productDao.findById(cartItemRequest.getProductId());
        if (!optionalProduct.isPresent()) {
            return "Error: Product not found";
        }
        Product product = optionalProduct.get();

        // Check if the cart already contains the product, update quantity if it does
        CartItem existingCartItem = cart.getCartItemByProductId(product.getId());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemRequest.getQuantity());
        } else {
            // Create new CartItem and add it to the cart
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(cartItemRequest.getQuantity());
            cartItem.setPrice(product.getPrice());
            cart.addCartItem(cartItem); // Associate the CartItem with the Cart
        }
        
        //calculate total amount and save
        cart.calculateTotalAmount();

        cartDao.save(cart);

        return "Product added to cart successfully";
    }
	
	/*
	public String addToCart(int customerId, CartItemRequest cartItemRequest) {
	    Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
	    if (!optionalCustomer.isPresent()) {
	        return "Error: Customer not found";
	    }
	    Customer customer = optionalCustomer.get();

	    // Check if the customer has a cart, create a new one if not
	    Cart cart = customer.getCart();
	    if (cart == null) {
	        cart = new Cart();
	        cart.setCustomer(customer);
	        customer.setCart(cart); // Associate cart with customer
	    }

	    Optional<Product> optionalProduct = productRepository.findById(cartItemRequest.getProductId());
	    if (!optionalProduct.isPresent()) {
	        return "Error: Product not found";
	    }
	    Product product = optionalProduct.get();

	    // Check stock availability
	    int requestedQuantity = cartItemRequest.getQuantity();
	    if (product.getStock() < requestedQuantity) {
	        return "Error: Insufficient stock. Only " + product.getStock() + " available.";
	    }

	    // Check if the cart already contains the product
	    CartItem existingCartItem = cart.getCartItemByProductId(product.getId());
	    if (existingCartItem != null) {
	        int newQuantity = existingCartItem.getQuantity() + requestedQuantity;

	        // Ensure stock is sufficient before updating quantity
	        if (product.getStock() < newQuantity) {
	            return "Error: Insufficient stock. Only " + product.getStock() + " available.";
	        }

	        existingCartItem.setQuantity(newQuantity);
	    } else {
	        // Create new CartItem and add it to the cart
	        CartItem cartItem = new CartItem();
	        cartItem.setProduct(product);
	        cartItem.setQuantity(requestedQuantity);
	        cartItem.setPrice(product.getPrice());
	        cart.addCartItem(cartItem);
	    }

	    // Deduct stock after successfully adding to the cart
	    product.setStock(product.getStock() - requestedQuantity);
	    productRepository.save(product); // Update stock in database

	    cart.calculateTotalPrice();
	    cartRepository.save(cart);

	    return "Product added to cart successfully";
	}
*/
	
	public List<CartItem> getCartItems(int customerId) {
        Optional<Customer> optionalCustomer = customerDao.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return new ArrayList<>();
        }
        Customer customer = optionalCustomer.get();

        Cart cart = customer.getCart();
        if (cart == null) {
            return new ArrayList<>(); // Return an empty list if cart is null
        }

        List<CartItem> cartItems = cart.getCartItems();
        
        // Encode image data to Base64 for each product
        return cartItems.stream()
        		.peek(item -> item.getProduct().encodeImageDataToBase64()) // Ensure that imageBase64 is populated
        		.collect(Collectors.toList());
	
	}
	
    public String checkoutCart(int customerId, CheckoutRequest checkoutRequest) {
        // Retrieve customer and cart
        Optional<Customer> optionalCustomer = customerDao.findById(customerId);
        if (!optionalCustomer.isPresent()) {
            return "Error: Customer not found";
        }
        Customer customer = optionalCustomer.get();
        Cart cart = customer.getCart();
        if (cart == null || cart.getCartItems().isEmpty()) {
            return "Error: Cart is empty";
        }

        // Create a new order
        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalAmount(cart.getTotalAmount()); // Set total amount from cart
        order.setDeliveryStatus("Delivered"); // Set initial delivery status
        order.setDeliveryAddress(checkoutRequest.getDeliveryAddress()); // Set delivery address
        
        order.setPlacedDate(new Date());// Set placed date
	
     
        orderDao.save(order);// Save the order
	
        
        // Clear the cart
        cart.getCartItems().clear();
        cart.calculateTotalAmount();
        cartDao.save(cart);

        return "Order placed successfully";
    }
    
    
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderDao.findByCustomerId(customerId);
    }
    
    public Customer getCustomerById(Integer id) {
        return customerDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    public Customer updateCustomer(Integer id, Customer updatedCustomer) {
        Customer existingCustomer = customerDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        existingCustomer.setAddress(updatedCustomer.getAddress());
        existingCustomer.setMobileNo(updatedCustomer.getMobileNo());

        return customerDao.save(existingCustomer);
    }
	
}
