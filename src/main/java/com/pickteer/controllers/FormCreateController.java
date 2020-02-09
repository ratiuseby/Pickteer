package com.pickteer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pickteer.service.QuestionService;

@Controller
public class FormCreateController {

	@Autowired QuestionService service;
	
	@GetMapping( "/user/form-create")
	public String getPage() {
		return "user/form-create";
	}
	
	@PostMapping( value="/user/form-create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String getForm(@RequestBody MultiValueMap<String, String> formData) {
		
		formData.forEach((k, v) -> System.out.println((k + ":" + v)));
		return "user/form-create";
	}
	
	@PostMapping( value="/user/form-save", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String saveForm(@RequestBody MultiValueMap<String, String> formData) {
		
		formData.forEach((k, v) -> System.out.println((k + ":" + v)));
		return "user/form-create";
	}
}
