package com.infy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.entities.User;
import com.infy.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		if (userRepository.findById(id).isPresent())
			userRepository.deleteById(id);
	}
	
	public User getUSerByName(String name) {
		return userRepository.findByUsername(name);
	}
}
