package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, Integer>{

}
