package com.farmbazaar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.farmbazaar.pojos.User;
import com.farmbazaar.service.LoginServiceImpl;
import com.farmbazaar.service.LoginServiceImpl.LoginRequest;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/farmbazaar")
public class LoginController {

	@Autowired
	private LoginServiceImpl loginService;
	
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        User user = loginService.authenticateUser(loginRequest);
        if (user != null) {
            return ResponseEntity.ok(user); // Return user data if login successful
        }

        // if Authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }
}
