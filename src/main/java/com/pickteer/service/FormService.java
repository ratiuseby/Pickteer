package com.pickteer.service;

import java.util.List;

import org.springframework.util.MultiValueMap;

import com.pickteer.models.Form;

public interface FormService {
	public void add(Form form);

	public void delete(long id);

	public List<Form> getForms();

	public Form getFormById(long id);

	public Form createForm(MultiValueMap<String, String> formData);
}
