package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {

}
