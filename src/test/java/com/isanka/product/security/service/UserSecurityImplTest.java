package com.isanka.product.security.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import com.isanka.product.security.dao.RoleRepo;
import com.isanka.product.security.dao.UserRepo;
import com.isanka.product.security.domain.AppUser;

@SpringBootTest
public class UserSecurityImplTest {

	@InjectMocks
	private UserSecurityImpl securityService = new UserSecurityImpl();
	@Mock
	private RoleRepo roleRepo;
	@Mock
	private UserRepo userRepo;
	
	
	@BeforeEach
	void setup() {
		AppUser testuser = new AppUser();
		testuser.setUserName("Isanka");
		testuser.setId(null);
		testuser.setPassword("1234");
		testuser.setName("Prabarshana");
		
		when(userRepo.findByUserName("Isanka")).thenReturn(testuser);
	}
	

	@Test
	public void testLoadUserByUsername() throws Exception {
		UserDetails returnUser = securityService.loadUserByUsername("Isanka");
		assertThat(returnUser.getUsername()).isEqualTo("Isanka");
	}

	@Test
	@Disabled
	public void testFetchUserByUserName() throws Exception {
	}

	@Test
	public void testSaveAppUser() throws Exception {
		
		AppUser testuser = new AppUser();
		testuser.setUserName("Isanka");
		testuser.setId(null);
		testuser.setPassword("1234");
		testuser.setName("Prabarshana");
		
		securityService.saveAppUser(testuser);
		
		ArgumentCaptor<AppUser> args = ArgumentCaptor.forClass(AppUser.class);
		verify(userRepo).save(args.capture());
		
		assertThat(args.getValue()).isEqualTo(testuser);
	}

	@Test
	@Disabled
	public void testFetchAllAppUsers() throws Exception {
	}

	@Test
	@Disabled
	public void testSaveAppRole() throws Exception {
	}

	@Test
	@Disabled
	public void testAssignRoleToUser() throws Exception {
	}
}