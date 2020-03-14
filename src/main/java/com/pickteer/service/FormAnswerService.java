package com.pickteer.service;

import java.util.List;

import com.pickteer.models.FormAnswer;

public interface FormAnswerService {
	public void add(FormAnswer form);

	public void delete(long id);

	public List<FormAnswer> getFormAnswers(long userId);

	public FormAnswer getFormById(long id);
}
