package com.isanka.product.security.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.isanka.product.security.domain.AppUser;

@DataJpaTest
public class UserRepoTest {
	
	@Autowired
	private UserRepo userRepo;
	
	@Test
	public void testFindByUserName() {
		//given
		AppUser testuser = new AppUser();
		testuser.setUserName("Isanka");
		testuser.setId(null);
		testuser.setPassword("1234");
		testuser.setName("Prabarshana");
		userRepo.save(testuser);
		//when
		AppUser returnuser = userRepo.findByUserName(testuser.getUserName());
		//then
		assertThat(returnuser).isSameAs(testuser);
	}
}