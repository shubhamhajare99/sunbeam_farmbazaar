package com.farmbazaar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Farmer;
import com.farmbazaar.pojos.Product;
import com.farmbazaar.service.FarmerService;

@RestController
@RequestMapping("/farmer")
@CrossOrigin(origins = "http://localhost:3000")
public class FarmerController {

	@Autowired
	private FarmerService farmerService;

	@PostMapping("/addfarmer")
	public ResponseEntity<?> addNewFarmer(@RequestBody Farmer transientCategory) {
		System.out.println("in add new category " + transientCategory);
		try {
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(farmerService
							.addNewFarmer(transientCategory));
		} catch (RuntimeException e) {
			return ResponseEntity.
					status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiResponse(e.getMessage()));
		}
	}
	
	/*
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProductsByCategory(@RequestParam String category) {
        List<Product> products = farmerService.getProductsByCategory(category);
        return ResponseEntity.ok(products);
    }
    */
    
    @GetMapping("/getallproducts")
    public List<Product> getAllProducts() {
      return farmerService.getAllProducts();
  }	

    @PostMapping("/products/add-quantity")
    public ResponseEntity<Void> addProductQuantity(@RequestParam int productId, @RequestParam double quantity) {
        farmerService.addProductQuantity(productId, quantity);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/{farmerId}/earnings")
    public ResponseEntity<Double> getTotalEarnings(@PathVariable Integer farmerId) {
        double totalEarnings = farmerService.calculateAndSaveTotalEarnings(farmerId);
        return ResponseEntity.ok(totalEarnings);
    }
}
