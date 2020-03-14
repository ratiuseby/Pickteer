package com.pickteer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickteer.models.FormAnswer;
import com.pickteer.repository.FormAnswerRepository;

@Service
public class FormAnswerServiceImpl implements FormAnswerService {

	@Autowired
	private FormAnswerRepository formAnswerRepository;

	@Override
	public void add(FormAnswer form) {
		formAnswerRepository.save(form);
	}

	@Override
	public void delete(long id) {
		formAnswerRepository.deleteById(id);
	}

	@Override
	public List<FormAnswer> getFormAnswers(long userId) {
		return formAnswerRepository.findByUserId(userId);
	}

	@Override
	public FormAnswer getFormById(long id) {
		Optional<FormAnswer> optionalForm = formAnswerRepository.findById(id);

		if (!optionalForm.isPresent()) {
			return new FormAnswer();
		} else {
			return optionalForm.get();
		}
	}

}
