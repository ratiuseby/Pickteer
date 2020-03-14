package com.pickteer.controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.pickteer.service.FormService;

@Controller
public class FormController {

	@Autowired
	FormService service;
	
	@PostMapping(value = Mappings.FORM, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String getFormAnswers(@RequestBody MultiValueMap<String, String> formData, Model model, @RequestParam Optional<String> id, Principal principal) {
		service.checkAnswers(formData, Integer.parseInt(id.get()), principal);
		
        return "redirect:" + Mappings.ROOT + "?id=" + id.orElse("") + "&message=thank-you";
    }

}
