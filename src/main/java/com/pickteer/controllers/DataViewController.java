package com.pickteer.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.pickteer.models.FormAnswer;
import com.pickteer.service.FormAnswerService;
import com.pickteer.service.UserService;

@Controller
public class DataViewController {

	@Autowired
	FormAnswerService formAnswerService;

	@Autowired
	UserService userService;
	
	@GetMapping(Mappings.VIEW_DATA)
	public String getPage(Model model, Principal principal) {
		HashMap<String, List<FormAnswer>> formAnswerMap = new HashMap<>();
		Long userId = userService.findByUsername(principal.getName()).getId();
		formAnswerService.getFormAnswers(userId).forEach(formAnswer -> {
			List<FormAnswer> list = formAnswerMap.get(formAnswer.getFormName());
			if(list != null) {
				list.add(formAnswer);
			} else {
				List<FormAnswer> newList = new ArrayList<>();
				newList.add(formAnswer);
				formAnswerMap.put(formAnswer.getFormName(), newList);
			}
		});
		model.addAttribute("forms", formAnswerMap);
		
		return "user/view";
	}

}
