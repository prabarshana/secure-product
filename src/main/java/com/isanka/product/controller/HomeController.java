package com.isanka.product.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping({"home", "index", "/"})
	public String loadHome() {
		logger.info("Loading home page..");
		return "home";
	}
	
	
	@RequestMapping("/menu")
	public String menu() {
		logger.info("Loading menu page..");
		return "menu";
	}

}
