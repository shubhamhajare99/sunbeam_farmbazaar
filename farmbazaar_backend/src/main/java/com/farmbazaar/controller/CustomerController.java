package com.farmbazaar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.dto.CartItemRequest;
import com.farmbazaar.dto.CheckoutRequest;
import com.farmbazaar.pojos.CartItem;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Order;
import com.farmbazaar.pojos.Product;
import com.farmbazaar.service.CustomerService;


@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/addcustomer")
	public ResponseEntity<?> addNewCustomer(@RequestBody Customer transientCategory) {
		System.out.println("in add new category " + transientCategory);
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(customerService
							.addNewCustomer(transientCategory));
					
		} catch (RuntimeException e) {
			return ResponseEntity.
					status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	
    @PostMapping("/cart/{customerId}/add")
    public ResponseEntity<?> addToCart(@PathVariable int customerId, @RequestBody CartItemRequest cartItemRequest) {
        String response = customerService.addToCart(customerId, cartItemRequest);
        if (response.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        return ResponseEntity.ok(response);
    }
    
    
    @GetMapping("/cart/{customerId}/items")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable int customerId) {
        List<CartItem> cartItems = customerService.getCartItems(customerId);
        if (cartItems.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok()
                //.contentType(MediaType.APPLICATION_JSON) //optional(Explicitly sets the response's Content-Type to application/json)
                .body(cartItems);
    }
    
    
    @PostMapping("/cart/{customerId}/checkout")
    public ResponseEntity<?> checkoutCart(@PathVariable int customerId, @RequestBody CheckoutRequest checkoutRequest) {
        String response = customerService.checkoutCart(customerId, checkoutRequest);
        if (response.startsWith("Error")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/{customerId}/orders")
    public ResponseEntity<List<Order>> getOrdersByCustomerId(@PathVariable int customerId) {
        List<Order> orders = customerService.getOrdersByCustomerId(customerId);
        return ResponseEntity.ok(orders);
    }
    
    
//    @GetMapping("/products")
//    public List<Product> getAllProducts() {
//        return customerService.getAllProducts();
//    }


	

	/*
	@PostMapping("/courses")
	public ResponseEntity<Product> createCourse(@RequestBody Product course){
		return new ResponseEntity<>(educationService.addCourse(course),HttpStatus.CREATED);
	}
	
	 @PutMapping("/courses/{id}")
	 public ResponseEntity<Product> updateCourse(@PathVariable Long id, @RequestBody Product updatedCourse) {
	    return new ResponseEntity<>(educationService.updateCourse(id, updatedCourse), HttpStatus.OK);
	}

	 @GetMapping("/courses")
	 public ResponseEntity<List<Product>> getCoursesByCategory(@RequestParam Role category) {
	     return new ResponseEntity<>(educationService.getCoursesByCategory(category), HttpStatus.OK);
	}
*/
	
	
	
}
