package com.isanka.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.isanka.product.security.domain.AppRole;
import com.isanka.product.security.domain.AppUser;
import com.isanka.product.security.service.UserSecurityService;

@SpringBootApplication
public class SecureProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecureProductApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(UserSecurityService service) {
//		return args -> {
//			
//			AppUser user1 = new AppUser();
//			user1.setUserName("isanka");
//			user1.setCompany("Home");
//			user1.setPassword("1234");
//			service.saveAppUser(user1);
//			
//			
//			AppRole role = new AppRole();
//			role.setName("ADMIN");
//			service.saveAppRole(role);
//			
//			AppRole role1 = new AppRole();
//			role1.setName("USER");
//			service.saveAppRole(role1);
//			
//			service.assignRoleToUser("isanka", "ADMIN");
//			service.assignRoleToUser("isanka", "USER");
//			
//			
//		};
//	}
}
