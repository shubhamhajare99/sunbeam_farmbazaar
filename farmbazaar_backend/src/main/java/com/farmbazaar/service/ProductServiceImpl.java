package com.farmbazaar.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbazaar.dao.ProductDao;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Product;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;

	public ApiResponse addNewProduct(Product transientCategory) {
		// set ID null to avoid StaleObjectStateException
		transientCategory.setPid(null);
		Product persistentCategory = productDao.save(transientCategory);
		return new ApiResponse("Added new product with ID " + persistentCategory.getPid());
	}
	
	public String deleteProduct(Integer productId) {
		if (productDao.existsById(productId)) {
			productDao.deleteById(productId);
			return "Deleted product details";
		}
		return "Invalid Product ID !!!!!";
	}
	
	public String updateProductDetails(Product detachedCategory) {
		// validate
		if (productDao.existsById(detachedCategory.getPid())) {
			productDao.save(detachedCategory);
			return "product updated !";
		}
		return "Product Updation Failed - invalid category ID";
	}
	
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
    
    
    public List<Product> getAllProductsForRole(String role) {
        List<Product> products = productDao.findAll();

        return products.stream()
                .map(product -> {
                    Product productWithAdjustedPrice = new Product();
                    productWithAdjustedPrice.setPid(product.getPid());
                    productWithAdjustedPrice.setName(product.getName());

                    if ("CUSTOMER".equalsIgnoreCase(role)) {
                        productWithAdjustedPrice.setPrice(product.getPrice() * 1.10);
                    } else { // FARMER
                        productWithAdjustedPrice.setPrice(product.getPrice());
                    }

                    return productWithAdjustedPrice;
                })
                .collect(Collectors.toList());

    }
}
    
