package com.isanka.product.security.service;

import java.util.Collection;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.isanka.product.security.domain.AppRole;
import com.isanka.product.security.domain.AppUser;

public interface UserSecurityService extends UserDetailsService{
	
	AppUser fetchUserByUserName(String userName);
	AppUser saveAppUser(AppUser appUser);
	Collection<AppUser> fetchAllAppUsers();
	
	AppRole saveAppRole(AppRole role);
	void assignRoleToUser(String userId, String role);

}
