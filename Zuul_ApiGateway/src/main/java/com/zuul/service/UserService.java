package com.zuul.service;

import java.util.List;

import com.zuul.domain.User;

public interface UserService {

	public User saveUser(User user);
	
	public List<User> getAllUsers();
	
	public User getUserById(Integer userId);
	
	public User getUserByUsername(String username);
}
