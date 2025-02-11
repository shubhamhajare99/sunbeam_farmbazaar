package com.farmbazaar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Product;
import com.farmbazaar.service.ProductService;

@RestController
@RequestMapping("/farmbazaar")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/addproduct")
	public ResponseEntity<?> addNewProduct(@RequestBody Product transientCategory) {
		System.out.println("in add new category " + transientCategory);
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(productService.addNewProduct(transientCategory));
					
		} catch (RuntimeException e) {
			return ResponseEntity.
					status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	@DeleteMapping("/{pid}")
	public String deleteProduct(@PathVariable Integer productId) {
		System.out.println("in delete product " + productId);
		return productService.deleteProduct(productId);
	}
	
	@PutMapping
	public String updateProductDetails(@RequestBody Product detachedCategory) {
		System.out.println("in update " + detachedCategory);
		return productService.updateProductDetails(detachedCategory);
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(@RequestParam String role) {
	    List<Product> products = productService.getAllProductsForRole(role);
	    return ResponseEntity.ok(products);
	}
}
