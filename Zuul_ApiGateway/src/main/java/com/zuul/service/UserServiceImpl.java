package com.zuul.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zuul.domain.User;
import com.zuul.respository.UserRepository;
@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public User saveUser(User user) {	
		return userRepo.save(user);		
	}

	@Override
	public List<User> getAllUsers() {		
		return userRepo.findAll();
	}

	@Override
	public User getUserById(Integer userId) {
		
		return userRepo.findOne(userId);
	}

	@Override
	public User getUserByUsername(String username) {

		return userRepo.findByUsername(username);
	}

}
