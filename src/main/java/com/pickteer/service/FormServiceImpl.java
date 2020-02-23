package com.pickteer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.pickteer.models.Form;
import com.pickteer.models.InputKeys;
import com.pickteer.models.LinearScaleQuestion;
import com.pickteer.models.MultipleChoiceQuestion;
import com.pickteer.models.Question;
import com.pickteer.models.SingleChoiceQuestion;
import com.pickteer.models.TextAreaQuestion;
import com.pickteer.repository.FormRepository;

@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private FormRepository formRepository;

	@Override
	public void add(Form form) {
		formRepository.save(form);
	}

	@Override
	public void delete(long id) {
		if(getFormById(id).getName() != null) {
			formRepository.deleteById(id);
		}
	}

	@Override
	public List<Form> getForms(long userId) {
		return formRepository.findByUserId(userId);
	}

	@Override
	public Form getFormById(long id) {
		Optional<Form> optionalForm = formRepository.findById(id);

		if (!optionalForm.isPresent()) {
			return new Form();
		} else {
			return optionalForm.get();
		}
	}

	@Override
	public Form createForm(MultiValueMap<String, String> formData) {

		Form form = new Form();

		Question question = new Question();
		LinearScaleQuestion linearScaleQuestion = new LinearScaleQuestion();
		SingleChoiceQuestion singleChoiceQuestion = new SingleChoiceQuestion();
		MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
		TextAreaQuestion textAreaQuestion = new TextAreaQuestion();

		formData.forEach((k, v) -> {
			if (k.equals(InputKeys.TITLE)) {
				form.setName(v.get(0));

			} else if (k.contains(InputKeys.QUESTION_TEXT)) {
				linearScaleQuestion.reset();
				singleChoiceQuestion.reset();
				multipleChoiceQuestion.reset();
				textAreaQuestion.reset();
				question.reset();
				question.setQuestionText(v.get(0));

			} else if (k.contains(InputKeys.LINEAR_SCALE_VALUE_LOW)) {
				linearScaleQuestion.setLowValue(Integer.valueOf(v.get(0)));

			} else if (k.contains(InputKeys.LINEAR_SCALE_VALUE_HIGH)) {
				linearScaleQuestion.setHighValue(Integer.valueOf(v.get(0)));

			} else if (k.contains(InputKeys.LINEAR_SCALE_LABEL_LOW)) {
				linearScaleQuestion.setLowLabel(v.get(0));

			} else if (k.contains(InputKeys.LINEAR_SCALE_LABEL_HIGH)) {
				linearScaleQuestion.setHighLabel(v.get(0));

			} else if (k.contains(InputKeys.SINGLE_ANSWER)) {
				singleChoiceQuestion.getAnswerList().add(v.get(0));

			} else if (k.contains(InputKeys.MULTIPLE_ANSWER)) {
				multipleChoiceQuestion.getAnswerList().add(v.get(0));

			} else if (k.contains(InputKeys.INPUT_DESIRED)) {
				question.setDesiredValue(v.get(0));
				
				if (!linearScaleQuestion.isEmpty()) {
					LinearScaleQuestion volatileLinearScaleQuestion = new LinearScaleQuestion(linearScaleQuestion);
					volatileLinearScaleQuestion.setQuestionText(question.getQuestionText());
					volatileLinearScaleQuestion.setDesiredValue(question.getDesiredValue());

					form.getQuestions().add(volatileLinearScaleQuestion);

				} else if (!singleChoiceQuestion.isEmpty()) {
					SingleChoiceQuestion volatileSingleChoiceQuestion = new SingleChoiceQuestion(singleChoiceQuestion);
					volatileSingleChoiceQuestion.setQuestionText(question.getQuestionText());
					volatileSingleChoiceQuestion.setDesiredValue(question.getDesiredValue());

					form.getQuestions().add(volatileSingleChoiceQuestion);

				} else if (!multipleChoiceQuestion.isEmpty()) {
					MultipleChoiceQuestion volatileMultipleChoiceQuestion = new MultipleChoiceQuestion(multipleChoiceQuestion);
					volatileMultipleChoiceQuestion.setQuestionText(question.getQuestionText());
					volatileMultipleChoiceQuestion.setDesiredValue(question.getDesiredValue());

					form.getQuestions().add(volatileMultipleChoiceQuestion);

				} else {
					TextAreaQuestion volatileTextAreaQuestion = new TextAreaQuestion();
					volatileTextAreaQuestion.setQuestionText(question.getQuestionText());
					volatileTextAreaQuestion.setDesiredValue(question.getDesiredValue());
					
					form.getQuestions().add(volatileTextAreaQuestion);

				}
			}
		});

		return form;
	}

}
