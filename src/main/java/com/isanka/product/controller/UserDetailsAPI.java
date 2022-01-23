package com.isanka.product.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isanka.product.security.domain.AppUser;
import com.isanka.product.security.dto.AppUserDto;
import com.isanka.product.security.service.UserSecurityService;

@RestController
@RequestMapping("/api")
public class UserDetailsAPI {
	
	@Autowired
	private UserSecurityService service;
	
	@GetMapping(path = "/users", produces = "application/json")
	public ResponseEntity<Collection<AppUser>> getUsers() {
		Collection<AppUser> userList = service.fetchAllAppUsers();
		return ResponseEntity.ok().body(userList);
	}
	
			
	@PostMapping(path = "/save")
	public ResponseEntity<AppUser> saveUser(@RequestBody AppUserDto userDto) throws URISyntaxException {
		
		AppUser user = new AppUser();
		user.setName(userDto.getName());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setCompany(userDto.getCompany());
		
		return ResponseEntity.created(new URI("/api/save")).body(service.saveAppUser(user));
	}

}
