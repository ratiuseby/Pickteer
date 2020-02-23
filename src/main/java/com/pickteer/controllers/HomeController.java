package com.pickteer.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pickteer.service.FormService;
import com.pickteer.service.UserService;

@Controller
public class HomeController {

	@Autowired
	FormService service;
	
	@Autowired
	UserService userService;

	@GetMapping("/user")
	public String getPage(Model model, Principal principal, @RequestParam Optional<String> id) {

		if(id.isPresent()) {
			service.delete(Integer.parseInt(id.get()));
		}

		Long userId = userService.findByUsername(principal.getName()).getId();
		model.addAttribute("forms", service.getForms(userId));
		
		return "user/home";
	}

}