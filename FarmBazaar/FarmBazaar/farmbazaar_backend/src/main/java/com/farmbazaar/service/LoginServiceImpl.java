package com.farmbazaar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.farmbazaar.dao.AdminDao;
import com.farmbazaar.dao.CustomerDao;
import com.farmbazaar.dao.FarmerDao;
import com.farmbazaar.pojos.User;
import lombok.Getter;
import lombok.Setter;


@Service
public class LoginServiceImpl {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private FarmerDao farmerDao;

    @Autowired
    private CustomerDao customerDao;
    
//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder; 


    public User authenticateUser(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();
        
        User user = adminDao.findByEmail(email);
        if (user == null) {
            user = farmerDao.findByEmail(email);
        }
        if (user == null) {
            user = customerDao.findByEmail(email);
        }
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
    
    // static inner class
    @Setter
    @Getter
    public static class LoginRequest {
        private String email;
        private String password;
        
    }
    
}   

/*
 * using optional
@Service
public class LoginServiceImpl {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private FarmerDao farmerDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder; 

    public User authenticateUser(LoginRequest loginRequest) {
    
    	String email = loginRequest.getEmail();
    	String password = loginRequest.getPassword();

    	// Search user in different repositories using Optional
    	Optional<? extends User> user = findUserByEmail(email);

    	// Verify password securely
    	if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
        return user.get();
    	}

    	return null; // Authentication failed
    }

    private Optional<? extends User> findUserByEmail(String email) {
    
    	Optional<Admin> admin = adminDao.findByEmail(email);
    	if (admin.isPresent()) {
        return admin; // Return Optional<Admin> 
    	}
    	
    	Optional<Farmer> farmer = farmerDao.findByEmail(email);
    	if (farmer.isPresent()) {
        return farmer; // Return Optional<Farmer> 
    	}
    
    	Optional<Customer> customer = customerDao.findByEmail(email);
    	if (customer.isPresent()) {
        return customer; // Return Optional<Customer>
    	}
    } 
    
    
    // static inner class
    @Setter
    @Getter
    public static class LoginRequest {
        private String email;
        private String password;
        
    }

}*/
