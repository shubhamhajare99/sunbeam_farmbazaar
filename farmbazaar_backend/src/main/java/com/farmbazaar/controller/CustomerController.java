package com.farmbazaar.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.farmbazaar.pojos.Role;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Product;
import com.farmbazaar.pojos.User;
import com.farmbazaar.service.CustomerService;


@RestController
@RequestMapping("/farmbazaar")
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
	
	
	
	
	
	
	
	
	
	
	
	/*@Autowired
	private EducationService educationService;
	
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
