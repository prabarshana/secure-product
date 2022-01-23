package com.isanka.product.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class CustomAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		Collection<String> ignoreList = new ArrayList<String>();
		ignoreList.add("/api/users");
		

		if (ignoreList.contains(request.getServletPath())) {
			String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

			if (authHeader != null && authHeader.startsWith("Bearer ")) {
				try {
					String token = authHeader.substring("Bearer ".length());
					DecodedJWT decode = JWT.require(Algorithm.HMAC256("isanka")).build().verify(token);
					
					Collection<SimpleGrantedAuthority> grants = new ArrayList<>();
					String user = decode.getSubject();
					decode.getClaim("roles").asList(String.class).forEach(claim -> {
						grants.add(new SimpleGrantedAuthority(claim));
					});
					UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, grants);
					SecurityContextHolder.getContext().setAuthentication(authToken);
					filterChain.doFilter(request, response);
					
				} catch (JWTVerificationException e) {
					//response.sendError(403);
					response.getWriter().write(e.getMessage());
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					//response.sendError(403);
					response.getWriter().write(e.getMessage());
					e.printStackTrace();
				}
			} else {
				response.sendError(403);
			}

		} else {
			filterChain.doFilter(request, response);
		}

	}

}
