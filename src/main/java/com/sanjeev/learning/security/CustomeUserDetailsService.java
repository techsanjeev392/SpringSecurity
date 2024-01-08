package com.sanjeev.learning.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.sanjeev.learning.model.User;
import com.sanjeev.learning.repository.UserRepository;

@Component
public class CustomeUserDetailsService implements UserDetailsService {
	
	@Autowired
	UserRepository userRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.findUserDetailsByEmailId(username);

		return user;
		
	
	}
	
	

}
