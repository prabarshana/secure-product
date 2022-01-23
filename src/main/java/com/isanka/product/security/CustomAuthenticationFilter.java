package com.isanka.product.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	Logger logger = LoggerFactory.getLogger(getClass());
	
	private AuthenticationManager manager;
	
	
	public CustomAuthenticationFilter(AuthenticationManager manager) {
		this.manager = manager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		logger.warn("Attempting Login...");
		
		
		String username = obtainUsername(request);
		username = (username != null) ? username : "";
		username = username.trim();
		String password = obtainPassword(request);
		password = (password != null) ? password : "";
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		return manager.authenticate(authRequest);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		User principal = (User) authResult.getPrincipal();
		logger.warn("{} logged in...", principal.getUsername());
		
		List<String> roles = new ArrayList<>();
		principal.getAuthorities().forEach(auth -> {
			roles.add(auth.getAuthority());
		});
		
		String access_token = JWT.create()
		.withSubject(principal.getUsername())
		.withIssuer("www.kaleris.com")
		.withIssuedAt(new Date())
		.withExpiresAt(new Date(System.currentTimeMillis() + (1000 * 60)))
		.withClaim("roles", roles)
		.sign(Algorithm.HMAC256("isanka"));
		
		logger.warn(access_token);
		
		response.setHeader("access_token", access_token);
		
		chain.doFilter(request, response);
		super.successfulAuthentication(request, response, chain, authResult);
	}


	
	
	
}
