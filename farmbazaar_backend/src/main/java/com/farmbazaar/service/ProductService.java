package com.farmbazaar.service;

import java.util.List;

import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Product;

public interface ProductService {

	public ApiResponse addNewProduct(Product transientCategory);
	public String deleteProduct(Integer productId);
	public String updateProductDetails(Product detachedCategory);
	public List<Product> getAllProducts();
	public List<Product> getAllProductsForRole(String role);

}
