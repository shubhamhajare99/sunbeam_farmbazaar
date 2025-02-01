package com.farmbazaar.service;

import java.util.List;

import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Product;


public interface CustomerService {

	public ApiResponse addNewCustomer(Customer transientCategory);
	public List<Product> getAllProducts();
	
}
