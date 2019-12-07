package com.pickteer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping( "/user")
	public String getPage() {
		return "user/home";
	}

}