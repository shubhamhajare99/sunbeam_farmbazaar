package com.farmbazaar.service;

import com.farmbazaar.dto.AuthRequestDTO;
import com.farmbazaar.dto.UserRespDTO;

public interface UserService {
	
	public UserRespDTO signIn(AuthRequestDTO dto);

}
