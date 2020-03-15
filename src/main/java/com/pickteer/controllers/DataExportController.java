package com.pickteer.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pickteer.models.FormAnswer;
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

		HashMap<FormAnswer, Integer> formAnswerMap = new HashMap<>();
		Long userId = userService.findByUsername(principal.getName()).getId();
		formAnswerService.getFormAnswers(userId).forEach(formAnswer -> {
			Integer lastValue = formAnswerMap.get(formAnswer) != null ? formAnswerMap.get(formAnswer) : 0;
			formAnswerMap.put(formAnswer, lastValue + 1);
		});
		model.addAttribute("forms", formAnswerMap);
		
		return "user/export";
	}
	
	@GetMapping(Mappings.DOWLOAD)
	public void getDownload(HttpServletResponse response, @RequestParam Optional<String> id) {
		if(id.isPresent()) {
			response.setContentType("application/octet-stream");
	        response.setHeader("Content-Disposition", "attachment; filename=answers.xlsx");
	        ByteArrayInputStream stream = formAnswerService.download(Integer.parseInt(id.get()));
	        try {
				IOUtils.copy(stream, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
