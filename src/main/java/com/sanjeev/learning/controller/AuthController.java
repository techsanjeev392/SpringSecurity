package com.sanjeev.learning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sanjeev.learning.helper.JwtUtil;
import com.sanjeev.learning.request.JwtAuthResponse;
import com.sanjeev.learning.request.JwtRequest;
import com.sanjeev.learning.security.CustomeUserDetailsService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CustomeUserDetailsService customeUseDetailsService;

	@PostMapping("/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest request) throws Exception {

		System.out.println("REsques :: " + request.toString());

		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentionals");
		} catch (BadCredentialsException e) {
			
			e.printStackTrace();
			throw new Exception("Bad Credentionals");
		}

		UserDetails userDetail = this.customeUseDetailsService.loadUserByUsername(request.getUsername());

		String token = this.jwtUtil.generateToken(userDetail);
		System.out.println("JWT :: " + token);

		JwtAuthResponse response = new JwtAuthResponse();

		response.setToken(token);

		return ResponseEntity.ok(response);
	}

}
