package com.isanka.product.security;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	
	Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@RequestMapping("/signIn")
	public String signIn() {
		logger.info("Loading sign in page..");
		return "signIn";
	}
	
	@RequestMapping("/login")
	public void login(HttpServletResponse response) throws IOException {
		logger.info("Redirecting to menu...");
		response.sendRedirect("/menu");
	}

}
