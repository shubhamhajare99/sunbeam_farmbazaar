package com.farmbazaar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Category;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Farmer;
import com.farmbazaar.pojos.Order;
import com.farmbazaar.pojos.Product;
import com.farmbazaar.service.AdminService;
import com.farmbazaar.service.ProductService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductService productService;

	// CRUD operations for categories

	@PostMapping("/categories")
	public Category createCategory(@RequestBody Category category) {
		return adminService.createCategory(category);
	}

	@PutMapping("/categories/{id}")
	public Category updateCategory(@PathVariable int id, @RequestBody Category categoryDetails) {
		return adminService.updateCategory(id, categoryDetails);
	}

	@DeleteMapping("/categories/{id}")
	public void deleteCategory(@PathVariable int id) {
		adminService.deleteCategory(id);
	}

	@GetMapping("/categories")
	public List<Category> getAllCategories() {
		return adminService.getAllCategories();
	}
	
	// CRUD operations for Products
	
	@PostMapping("/products")
	public Product addNewProduct(@RequestParam("name") String name, @RequestParam("price") double price,
			@RequestParam("quantity") double quantity, @RequestParam("category_id") int categoryId, @RequestParam("imageFile") MultipartFile imageFile) {
		return productService.addNewProduct(name, price, quantity, categoryId, imageFile);
	}
	/*
	@PostMapping("/products")
    public ApiResponse addNewProduct(@RequestBody Product product, @RequestParam Integer categoryId) {
        return productService.addNewProduct(product, categoryId);
    }
    */
	
	@PutMapping("/products/{id}")
	public Product updateProduct(@PathVariable int id, @RequestParam(required = false) MultipartFile imageFile,
			@RequestParam("name") String name, @RequestParam("price") double price,
			@RequestParam("quantity") double quantity) {
		return productService.updateProductDetails(id, imageFile, name, price, quantity);
	}
	/*
	@PutMapping("/products/{id}")
	public ResponseEntity<String> updateProduct(@PathVariable Integer id, @RequestBody Product product) {
	    product.setPid(id); // injecting the correct id into the product object so that right product is updated
	    String response = productService.updateProductDetails(product);
	    if (response.equals("product updated !")) {
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}*/

	
	@DeleteMapping("/products/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
	    String response = productService.deleteProduct(id);
	    if (response.equals("Deleted product details")) {
	        return ResponseEntity.ok(response);
	    } else {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}
	
	@GetMapping("/products")
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	
	// CRUD operations for Customer users

	@DeleteMapping("/customer-users/{id}")
	public void deleteCustomerUser(@PathVariable int id) {
		adminService.deleteCustomerUser(id);
	}

	@GetMapping("/customer-users")
	public List<Customer> getAllCustomerUsers() {
		return adminService.getAllCustomerUsers();
	}
	
	// CRUD operations for Farmer users
	
	@DeleteMapping("/farmer-users/{id}")
	public void deleteFarmerUser(@PathVariable int id) {
		adminService.deleteFarmerUser(id);
	}

	@GetMapping("/farmer-users")
	public List<Farmer> getAllFarmerUsers() {
		return adminService.getAllFarmerUsers();
	}
	
	// Method to get all orders
	@GetMapping("/orders")
	public List<Order> getAllOrders() {
		return adminService.getAllOrders();
	}
	
}
