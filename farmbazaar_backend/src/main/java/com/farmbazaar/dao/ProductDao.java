package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
