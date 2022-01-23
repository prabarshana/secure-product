package com.isanka.product.security.dao;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.isanka.product.security.domain.AppRole;

@DataJpaTest
public class RoleRepoTest {

	
	@Autowired
	private RoleRepo roleRepo;
	

 	@Test
	public void testFindByName() throws Exception {
 		AppRole role = new AppRole();
 		role.setName("TestRole");
 		
 		roleRepo.save(role);
 		
 		AppRole returnRole = roleRepo.findByName(role.getName());
 		assertThat(returnRole).isSameAs(role);

	}
 	
 	@Test
	public void anotherTest() throws Exception {

 		AppRole role = new AppRole();
 		role.setName("TestRole");
 		
 		roleRepo.save(role);
 		
 		AppRole returnRole = roleRepo.findByName(role.getName());
 		assertThat(returnRole).isSameAs(role);


	}
}