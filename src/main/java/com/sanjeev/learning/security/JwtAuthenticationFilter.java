package com.sanjeev.learning.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	UserDetailsService userdetailsService;
	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 1. get Token

		String requestToken = request.getHeader("Authorization");
		log.info("REquest token {}", requestToken);

		// Bearer 1345tds;
		String user = null;
		String token = null;
		if (requestToken != null && requestToken.startsWith("Bearer")) {
			token = requestToken.substring(7);
			try {
				user = this.jwtTokenHelper.getUsernameFromToken(token);

			} catch (IllegalArgumentException e) {
				log.info("IllegalArgumentException ::" + e.getMessage());

			} catch (ExpiredJwtException e) {
				log.info("ExpiredJwtException ::" + e.getMessage());

			} catch (MalformedJwtException e) {
				log.info("MalformedJwtException ::" + e.getMessage());
			}

			// Validation of the Token;

			if (user != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = userdetailsService.loadUserByUsername(user);
				if (this.jwtTokenHelper.validateToken(token, userDetails)) {
					// going good
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				} else {
					log.info("Invalid Jwt Token");
				}

			}
		}

		filterChain.doFilter(request, response);

	}

}
