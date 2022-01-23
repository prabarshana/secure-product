package com.isanka.product.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isanka.product.security.domain.AppRole;

@org.springframework.stereotype.Repository
public interface RoleRepo extends JpaRepository<AppRole, Long> {
	
	AppRole findByName(String name);

}
