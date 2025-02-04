package com.farmbazaar.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.farmbazaar.custom_exception.AuthenticationException;
import com.farmbazaar.dto.AuthRequestDTO;
import com.farmbazaar.dto.UserRespDTO;
import com.farmbazaar.pojos.User;

import jakarta.transaction.Transactional;

/*
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@Override
	public UserRespDTO signIn(AuthRequestDTO dto) {
		// 1. invoke dao's method
		User userEntity = userDao.findByEmailAndPassword
				(dto.getEmail(), dto.getPassword())
				.orElseThrow(() -> new AuthenticationException("Invalid Email or password !!!!!"));
		
		// user entity : persistent -> dto
		return modelMapper.map(userEntity, UserRespDTO.class);
	}
}*/
