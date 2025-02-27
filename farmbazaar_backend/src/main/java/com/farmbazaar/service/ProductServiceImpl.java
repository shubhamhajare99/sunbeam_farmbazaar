package com.farmbazaar.service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.farmbazaar.dao.CategoryDao;
import com.farmbazaar.dao.ProductDao;
import com.farmbazaar.dto.ApiResponse;
import com.farmbazaar.pojos.Category;
import com.farmbazaar.pojos.Product;

import jakarta.transaction.Transactional;



@Service
@Transactional
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;

	@Autowired
    private CategoryDao categoryDao;

    public Product addNewProduct(String name, double price, double quantity,
            int categoryId, MultipartFile imageFile) {
    		Product product = new Product();
    		product.setName(name);
    		product.setPrice(price);
    		product.setQuantity(quantity);

    		Category category = fetchCategoryById(categoryId);
    		product.setCategory(category);

    		try {
    			if (imageFile != null) {
    			product.setImage(imageFile.getBytes());
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}

    		return productDao.save(product);
    }
    
    public Category fetchCategoryById(int categoryId) {
        return categoryDao.findById(categoryId).orElse(null);
    }
	
	
	public String deleteProduct(Integer productId) {
		if (productDao.existsById(productId)) {
			productDao.deleteById(productId);
			return "Deleted product details";
		}
		return "Invalid Product ID !!!!!";
	}
	
    public Product updateProductDetails(int id, MultipartFile imageFile, String name, double price, double quantity) {
        Product product = productDao.findById(id).orElse(null);
        if (product != null) {
            try {
                if (imageFile != null) {
                    product.setImage(imageFile.getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            return productDao.save(product);
        }
        return null;
    }
	
    /*
	public String updateProductDetails(Product detachedCategory) {
		// validate
		if (productDao.existsById(detachedCategory.getPid())) {
			productDao.save(detachedCategory);
			return "product updated !";
		}
		return "Product Updation Failed - invalid category ID";
	}
	*/
	
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }
    
    
    
    public List<Product> getAllProductsForRole(String role) {
        List<Product> products = productDao.findAll();

        return products.stream()
                .map(product -> {
                    Product productWithAdjustedPrice = new Product();
                    productWithAdjustedPrice.setId(product.getId());
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
    
