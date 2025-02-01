package com.farmbazaar.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.farmbazaar.pojos.Farmer;

public interface FarmerDao extends JpaRepository<Farmer, Integer>{

}
