package com.zuul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zuul.domain.User;
import com.zuul.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public void saveUser(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		userService.saveUser(user);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.getAllUsers();		
	}
}
