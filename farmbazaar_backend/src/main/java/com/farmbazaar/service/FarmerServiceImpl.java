package com.farmbazaar.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.farmbazaar.dao.CategoryDao;
import com.farmbazaar.dao.FarmerDao;
import com.farmbazaar.dao.ProductDao;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Category;
import com.farmbazaar.pojos.Farmer;
import com.farmbazaar.pojos.Product;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class FarmerServiceImpl implements  FarmerService {
    @Autowired
    private ProductDao productDao;
    
    @Autowired
    private FarmerDao farmerDao;
    
    @Autowired
    private CategoryDao categoryDao;
    
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
	public ApiResponse addNewFarmer(Farmer transientCategory) {
    	
   	  // Encrypt password before saving
//	    transientCategory.setPassword(passwordEncoder.encode(transientCategory.getPassword()));
		transientCategory.setId(null);
		Farmer persistentCategory = farmerDao.save(transientCategory);
		return new ApiResponse("Added new catgroy with ID " + persistentCategory.getId());
	}
/*
    @Override
    public List<Product> getProductsByCategory(String categoryName) {
        Category category = categoryDao.findByCatName(categoryName);
        if (category == null) {
            throw new RuntimeException("Category not found");
        }
        return category.getProducts();
    }
    */
    @Override
	public List<Product> getAllProducts() {
		List<Product> productsList = productDao.findAll();
		return productsList;
	}

    @Override
    public void addProductQuantity(int productId, double quantity) {
        Product product = productDao.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setQuantity(product.getQuantity() + quantity);
        productDao.save(product);
    }
    
    
    public double calculateAndSaveTotalEarnings(Integer farmerId) {
        Optional<Farmer> farmerOpt = farmerDao.findById(farmerId);
        if (farmerOpt.isPresent()) {
            Farmer farmer = farmerOpt.get();
            double totalEarnings = 0;
            for (Product product : farmer.getProductsList()) {
                totalEarnings += product.getPrice();
            }
            farmer.setTotalRevenue(totalEarnings);
            farmerDao.save(farmer);
            return totalEarnings;
        } else {
            throw new RuntimeException("Farmer not found with id: " + farmerId);
        }
    }

}

