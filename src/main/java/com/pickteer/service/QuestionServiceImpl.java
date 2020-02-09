package com.pickteer.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickteer.models.Question;
import com.pickteer.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public void add(Question question) {
		questionRepository.save(question);
	}

	@Override
	public void delete(long id) {
		questionRepository.deleteById(id);
	}

	@Override
	public List<Question> getQuestions() {
        return (List<Question>) questionRepository.findAll();
	}

	@Override
	public Question getQuestionById(long id) throws NoSuchElementException {
		Optional<Question> optionalQuestion = questionRepository.findById(id);
        return optionalQuestion.get();
	}

}
