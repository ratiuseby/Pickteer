package com.pickteer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormCreateController {

	@GetMapping( "/user/form-create")
	public String getPage() {
		return "user/form-create";
	}
}
