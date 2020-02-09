package com.pickteer.service;

import java.util.List;

import com.pickteer.models.Question;

public interface QuestionService {
	public void add(Question question);
    public void delete(long id);
    public List<Question> getQuestions();
    public Question getQuestionById(long id);
}
