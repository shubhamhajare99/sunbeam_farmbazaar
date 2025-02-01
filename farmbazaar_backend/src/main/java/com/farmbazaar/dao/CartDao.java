package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

}
