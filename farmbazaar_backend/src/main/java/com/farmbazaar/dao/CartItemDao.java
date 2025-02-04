package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.CartItem;

public interface CartItemDao extends JpaRepository<CartItem, Integer> {

}
