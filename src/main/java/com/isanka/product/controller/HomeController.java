package com.isanka.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@RequestMapping({"home", "index", "/"})
	@ResponseBody
	public String loadHome() {
		
		return "This is home";
	}

}
