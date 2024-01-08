package com.sanjeev.learning.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sanjeev.learning.model.User;
import com.sanjeev.learning.repository.UserRepository;
import com.sanjeev.learning.request.UserRequest;

@Component
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUserDetails() {

		return userRepository.findAllusers();

	}

	public String saveUserDetails(UserRequest userRequest) {
		User user = new User();
		try {
			user.setEmailId(userRequest.getEmailId());
			user.setPassword(userRequest.getPassword());
			user.setUserName(userRequest.getUserName());
			userRepository.save(user);
			return "User SAved SuccessFully";
		} catch (Exception e) {
			return "User Save failed " + e.getMessage();
		}
	}
}
