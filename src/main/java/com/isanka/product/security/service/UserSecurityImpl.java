package com.isanka.product.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityImpl implements UserSecurityService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return User.withDefaultPasswordEncoder().username("isanka").password("123").authorities("ADMIN").build();
	}

}
