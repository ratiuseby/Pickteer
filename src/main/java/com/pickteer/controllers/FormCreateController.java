package com.pickteer.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class FormCreateController {

	@GetMapping( "/user/form-create")
	public String getPage() {
		return "user/form-create";
	}
	
	@PostMapping( value="/user/form-create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String getForm(@RequestBody MultiValueMap<String, String> formData) {
		
		formData.forEach((k, v) -> System.out.println((k + ":" + v)));
		return "user/form-create";
	}
}
