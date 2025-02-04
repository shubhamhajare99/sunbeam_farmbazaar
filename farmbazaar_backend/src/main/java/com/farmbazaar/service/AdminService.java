package com.farmbazaar.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.farmbazaar.dto.UserDetailsDTO;
import com.farmbazaar.pojos.Category;
import com.farmbazaar.pojos.Customer;
import com.farmbazaar.pojos.Farmer;
import com.farmbazaar.pojos.Order;

public interface AdminService {

	public void deleteCustomerUser(int id);
	public List<Customer> getAllCustomerUsers();
	public void deleteFarmerUser(int id);
	public List<Farmer> getAllFarmerUsers();
	public Category createCategory(Category category);
	public Category updateCategory(int id, Category categoryDetails);
	public void deleteCategory(int id);
	public List<Category> getAllCategories();
	public List<Order> getAllOrders();
	public ResponseEntity<List<UserDetailsDTO>> getUserById(int id);
	
}
