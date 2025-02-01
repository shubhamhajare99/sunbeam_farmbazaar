package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer> {

}
