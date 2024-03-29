package com.avin.api.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avin.api.repository.UserRepository;
import com.avin.entity.User;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Transactional
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public Optional<User> getUser(Long id) {
		return userRepository.findById(id);
	}
}
