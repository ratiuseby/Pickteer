package com.pickteer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pickteer.service.FormService;

@Controller
public class FormCreateController {

	@Autowired
	FormService service;

	@GetMapping(Mappings.USER_FORM_CREATE)
	public String getPage() {
		return Mappings.USER_FORM_CREATE;
	}

	@PostMapping(value = Mappings.USER_FORM_CREATE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String getForm(@RequestBody MultiValueMap<String, String> formData) {
		service.add(service.createForm(formData));
		return Mappings.USER_FORM_CREATE;
	}
}
