package com.sanjeev.learning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanjeev.learning.model.User;
import com.sanjeev.learning.request.UserRequest;
import com.sanjeev.learning.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users/details")
	List<User> getAllUsers() {
		return userService.getAllUserDetails();
	}
	
	@PostMapping("/user")
	String postUser(@RequestBody UserRequest userRequest) {
		return userService.saveUserDetails(userRequest);
	}

}
