package com.isanka.product.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	
	@RequestMapping("/login")
	public String login() {
		logger.info("Authenticating the user...");
		return "login";
	}
	
	
	@RequestMapping("/user")
	@ResponseBody
	public Authentication getUser(Authentication user){
		logger.info("Returning  the authenticated user...");
		return user;
	}
	
	@RequestMapping("/logout")
	public void logout() {
		logger.info("Logging out...");
	}
	
	@RequestMapping("/errorlogin")
	public String errorlogin() {
		logger.info("False login");
		return "loginFailure";
	}
	


}
