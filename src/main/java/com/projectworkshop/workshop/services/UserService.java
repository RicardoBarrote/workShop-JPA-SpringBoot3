package com.projectworkshop.workshop.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectworkshop.workshop.entities.User;
import com.projectworkshop.workshop.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById (Long id) {
		Optional<User> opt = userRepository.findById(id);
		return opt.get();
	}
}
