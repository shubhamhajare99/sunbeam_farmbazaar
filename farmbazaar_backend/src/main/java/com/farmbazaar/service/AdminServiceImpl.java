package com.farmbazaar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.farmbazaar.dao.CategoryDao;
import com.farmbazaar.dao.CustomerDao;
import com.farmbazaar.dao.FarmerDao;
import com.farmbazaar.dao.OrderDao;
import com.farmbazaar.dao.ProductDao;
import com.farmbazaar.dto.UserDetailsDTO;
import com.farmbazaar.pojos.Category;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Farmer;
import com.farmbazaar.pojos.Order;
import com.farmbazaar.pojos.Role;


@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private FarmerDao farmerDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDao orderDao;
	

    public void deleteCustomerUser(int id) {
        customerDao.deleteById(id);
    }

    public List<Customer> getAllCustomerUsers() {
        return customerDao.findAll();
    }
    
 
    public void deleteFarmerUser(int id) {
        farmerDao.deleteById(id);
    }

    public List<Farmer> getAllFarmerUsers() {
        return farmerDao.findAll();
    }
    
    
    // CRUD operations for categories

    public Category createCategory(Category category) {
        return categoryDao.save(category);
    }

    public Category updateCategory(int id, Category categoryDetails) {
        Category category = categoryDao.findById(id).orElse(null);
        if (category != null) {
            category.setName(categoryDetails.getName());
           
            return categoryDao.save(category);
        }
        return null; // if category with specified id not found
    }

    public void deleteCategory(int id) {
        categoryDao.deleteById(id);
    }

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }
	
    // Method to get all orders
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }
    
    // Method to get user details by ID for all user types
    
    public ResponseEntity<List<UserDetailsDTO>> getUserById(int id) {
        
    	//emptylist to hold userDetailsDTO object
    	List<UserDetailsDTO> userDetailsList = new ArrayList<>();

        farmerDao.findById(id).ifPresent(farmer -> {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setId(farmer.getId());
            userDetails.setEmail(farmer.getEmail());
            userDetails.setRole(Role.FARMER.toString());
            userDetailsList.add(userDetails);//populated UserDetailsDTO object is added to that list
        });

        customerDao.findById(id).ifPresent(customer -> {
            UserDetailsDTO userDetails = new UserDetailsDTO();
            userDetails.setId(customer.getId());
            userDetails.setEmail(customer.getEmail());
            userDetails.setRole(Role.CUSTOMER.toString());
            userDetailsList.add(userDetails);
        });

        if (userDetailsList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userDetailsList);
        }
    }
	
}
