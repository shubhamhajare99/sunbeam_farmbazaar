package com.farmbazaar.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Category;
import com.farmbazaar.pojos.Product;

public interface ProductService {

	public String deleteProduct(Integer productId);
	//public String updateProductDetails(Product detachedCategory);
	public List<Product> getAllProducts();
	public List<Product> getAllProductsForRole(String role);
	public Product addNewProduct(String name, double price, double quantity, int categoryId, MultipartFile imageFile);
	public Product updateProductDetails(int id, MultipartFile imageFile, String name, double price, double quantity);
	public Category fetchCategoryById(int categoryId);
}
