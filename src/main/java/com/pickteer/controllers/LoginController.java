package com.pickteer.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pickteer.models.Form;
import com.pickteer.service.FormService;

@Controller
public class LoginController {
	
	@Autowired
	FormService service;
	
	@GetMapping(Mappings.ROOT)
    public String index(Model model, @RequestParam Optional<String> id) {
		if(id.isPresent()) {
			try {
				Form formById = service.getFormById(Integer.valueOf(id.get()));
				
				if(formById.getName().isEmpty()) {
					return Mappings.INDEX;
				} else {
					model.addAttribute("form", formById);
					return "form";
				}
			}
			catch (Exception e) {
				return Mappings.INDEX;
			}
		} else {
			return Mappings.INDEX;
		}
    }
	
	@GetMapping( Mappings.ACCESS_DENIED)
    public String accessDenied() {
        return "error/access-denied";
    }
	
}

