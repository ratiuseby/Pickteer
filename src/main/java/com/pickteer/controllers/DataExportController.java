package com.pickteer.controllers;

import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pickteer.service.FormAnswerService;
import com.pickteer.service.UserService;

@Controller
public class DataExportController {
	
	@Autowired
	FormAnswerService formAnswerService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(Mappings.EXPORT_DATA)
	public String getPage(Model model, Principal principal, @RequestParam Optional<String> id) {

		if(id.isPresent()) {
			formAnswerService.delete(Integer.parseInt(id.get()));
		}

		HashMap<String, Integer> formAnswerMap = new HashMap<>();
		Long userId = userService.findByUsername(principal.getName()).getId();
		formAnswerService.getFormAnswers(userId).forEach(formAnswer -> {
			Integer lastValue = formAnswerMap.get(formAnswer.getFormName()) != null ? formAnswerMap.get(formAnswer.getFormName()) : 0;
			formAnswerMap.put(formAnswer.getFormName(), lastValue + 1);
		});
		model.addAttribute("forms", formAnswerMap);
		
		return "user/export";
	}
}
