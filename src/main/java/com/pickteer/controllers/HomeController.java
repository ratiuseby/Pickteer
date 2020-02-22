package com.pickteer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pickteer.service.FormService;

@Controller
public class HomeController {

	@Autowired
	FormService service;

	@GetMapping("/user")
	public String getPage(Model model) {

		service.getForms().forEach((k) -> System.out.println(k));
		model.addAttribute("forms", service.getForms());

		return "user/home";
	}

}