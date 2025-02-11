package com.farmbazaar.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farmbazaar.pojos.Order;

@Repository
public interface OrderDao extends JpaRepository<Order, Integer > {

	List<Order> findByCustomerId(int customerId);
}
