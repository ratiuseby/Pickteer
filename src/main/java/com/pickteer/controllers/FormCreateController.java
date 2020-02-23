package com.pickteer.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.pickteer.models.Form;
import com.pickteer.service.FormService;
import com.pickteer.service.UserService;

@Controller
public class FormCreateController {

	@Autowired
	FormService service;
	
	@Autowired
	UserService userService;

	@GetMapping(Mappings.USER_FORM_CREATE)
	public String getPage() {
		return Mappings.USER_FORM_CREATE;
	}

	@PostMapping(value = Mappings.USER_FORM_CREATE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String getForm(@RequestBody MultiValueMap<String, String> formData, Principal principal) {
		Form form = service.createForm(formData);
		form.setUser(userService.findByUsername(principal.getName()));
		service.add(form);
		return Mappings.USER_FORM_CREATE;
	}
}
