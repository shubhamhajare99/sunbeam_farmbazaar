package com.farmbazaar.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.Cart;

public interface CartDao extends JpaRepository<Cart, Integer> {

	Optional<Cart> findByCustomer_Id(int customerId);
}
