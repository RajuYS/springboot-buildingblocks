package com.infy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.infy.entities.User;
import com.infy.exceptions.UserExistsException;
import com.infy.exceptions.UserNotFoundException;
import com.infy.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User createUser(User user) throws UserExistsException {
		User existingUser = userRepository.findByUsername(user.getUsername());
		if (existingUser != null)
			throw new UserExistsException("User already exists in repository");
		return userRepository.save(user);
	}

	public Optional<User> getUserById(Long id) throws UserNotFoundException {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new UserNotFoundException("UserId is not found in repository");
		return user;
	}

	public User updateUserById(Long id, User user) throws UserNotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new UserNotFoundException("User not found in repository, provide the correct user id");
		}
		user.setId(id);
		return userRepository.save(user);
	}

	public void deleteUserById(Long id) {
		// if (userRepository.findById(id).isPresent())
		Optional<User> optionalUser = userRepository.findById(id);
		if (!optionalUser.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"User not found in repository, provide the correct user id");
		}
		userRepository.deleteById(id);
	}

	public User getUSerByName(String name) {
		return userRepository.findByUsername(name);
	}
}
