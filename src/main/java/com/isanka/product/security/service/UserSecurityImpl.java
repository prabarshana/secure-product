package com.isanka.product.security.service;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isanka.product.security.dao.RoleRepo;
import com.isanka.product.security.dao.UserRepo;
import com.isanka.product.security.domain.AppRole;
import com.isanka.product.security.domain.AppUser;

@Service
@Transactional
public class UserSecurityImpl implements UserSecurityService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		AppUser user = fetchUserByUserName(username);
		
		if (user == null) {
			logger.error("user {} not found", username);
			throw new UsernameNotFoundException("user not found");
		} else {
			Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
			user.getRoles().forEach(role -> {
				authorities.add(new SimpleGrantedAuthority(role.getName()));
			});
			
			return User.withUsername(user.getUserName()).password(user.getPassword()).authorities(authorities).build();
			
		}
	}

	@Override
	public AppUser fetchUserByUserName(String userName) {
		return userRepo.findByUserName(userName);
	}

	@Override
	public AppUser saveAppUser(AppUser appUser) {
		appUser.setPassword(new BCryptPasswordEncoder().encode(appUser.getPassword()));
		return userRepo.save(appUser);
	}

	@Override
	public Collection<AppUser> fetchAllAppUsers() {
		return userRepo.findAll();
	}

	@Override
	public AppRole saveAppRole(AppRole role) {
		return roleRepo.save(role);
	}

	@Override
	public void assignRoleToUser(String userName, String role) {
		
		AppUser user = userRepo.findByUserName(userName);
		AppRole appRole = roleRepo.findByName(role);
		user.getRoles().add(appRole);
	}

}
