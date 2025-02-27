package com.farmbazaar.service;

import java.util.List;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Farmer;
import com.farmbazaar.pojos.Product;

public interface FarmerService {

	public ApiResponse addNewFarmer(Farmer transientCategory);
	
	//public List<Product> getProductsByCategory(String categoryName);
	
	public void addProductQuantity(int productId, double quantity);
	
    public double calculateAndSaveTotalEarnings(Integer farmerId);

	public List<Product> getAllProducts();
}
