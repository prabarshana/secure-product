package com.isanka.product.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isanka.product.security.domain.AppUser;

@org.springframework.stereotype.Repository
public interface UserRepo extends JpaRepository<AppUser, Long> {
	 AppUser findByUserName(String userName);
}
