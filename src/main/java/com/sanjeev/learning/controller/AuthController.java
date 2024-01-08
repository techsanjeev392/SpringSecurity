package com.sanjeev.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanjeev.learning.request.JwtAuthResponse;
import com.sanjeev.learning.request.JwtRequest;
import com.sanjeev.learning.security.CustomeUserDetailsService;
import com.sanjeev.learning.security.JwtTokenHelper;

@RestController
public class AuthController {

	@Autowired
	JwtTokenHelper jwttokenHelper;

	@Autowired
	CustomeUserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/token")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtRequest request) {

		this.authticate(request.getUsername(), request.getPasword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwttokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);

		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authticate(String username, String pasword) {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				pasword);
		authenticationManager.authenticate(authenticationToken);

	}
}
