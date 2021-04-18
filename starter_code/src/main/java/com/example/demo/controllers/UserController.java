package com.example.demo.controllers;

import java.util.Optional;

import com.example.demo.security.SecurityConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping("/id/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		return ResponseEntity.of(userRepository.findById(id));
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<User> findByUserName(@PathVariable String username) {
		return ResponseEntity.of(userRepository.findOptionalByUsername(username));
	}
	
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
		if (null == createUserRequest.getPassword()
			|| null == createUserRequest.getConfirmPassword()
			|| null == createUserRequest.getUsername()) {
			log.error("Invalid create User request, must include username, password, and confirmPassword");
			return ResponseEntity.badRequest().build();
		}

		if (createUserRequest.getPassword().length() < SecurityConstants.MIN_PASSWORD_LENGTH) {
			log.error("Password must be at least {} characters", SecurityConstants.MIN_PASSWORD_LENGTH);
			return ResponseEntity.badRequest().build();
		}

		if (!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
			log.error("Password and confirmPassword do not match");
			return ResponseEntity.badRequest().build();
		}

		User user = new User();
		user.setUsername(createUserRequest.getUsername());
		Cart cart = new Cart();
		cartRepository.save(cart);
		user.setCart(cart);
		user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		userRepository.save(user);
		log.info("New user has been saved, username: {}", user.getUsername());
		return ResponseEntity.ok(user);
	}
	
}
