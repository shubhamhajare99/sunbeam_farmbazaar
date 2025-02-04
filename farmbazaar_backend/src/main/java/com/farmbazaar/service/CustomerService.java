package com.farmbazaar.service;

import java.util.List;

import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.dto.CartItemRequest;
import com.farmbazaar.dto.CheckoutRequest;
import com.farmbazaar.pojos.CartItem;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Order;
import com.farmbazaar.pojos.Product;


public interface CustomerService {

	public ApiResponse addNewCustomer(Customer transientCategory);
	public String addToCart(int customerId, CartItemRequest cartItemRequest);
	public List<CartItem> getCartItems(int customerId);
	public String checkoutCart(int customerId, CheckoutRequest checkoutRequest);
	public List<Order> getOrdersByCustomerId(int customerId);
	public List<Product> getAllProducts();
	
}
