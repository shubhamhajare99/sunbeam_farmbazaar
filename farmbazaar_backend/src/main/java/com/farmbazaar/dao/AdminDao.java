package com.farmbazaar.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import com.farmbazaar.pojos.Admin;

public interface AdminDao extends JpaRepository<Admin, Integer>{

	Admin findByEmail(String email);

}
