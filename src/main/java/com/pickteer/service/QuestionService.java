package com.pickteer.service;

import java.util.List;

import com.pickteer.models.Question;

public interface QuestionService {
	public void add(Question form);

	public void delete(long id);

	public List<Question> getQuestions();

	public Question getQuestionById(long id);
}
