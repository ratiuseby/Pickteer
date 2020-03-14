package com.pickteer.service;

import java.security.Principal;
import java.util.List;

import org.springframework.util.MultiValueMap;

import com.pickteer.models.Form;

public interface FormService {
	public void add(Form form);

	public void delete(long id);

	public List<Form> getForms(long userId);

	public Form getFormById(long id);

	public Form createForm(MultiValueMap<String, String> formData);
	
	public void checkAnswers(MultiValueMap<String, String> formData, long id, Principal principal);
}
