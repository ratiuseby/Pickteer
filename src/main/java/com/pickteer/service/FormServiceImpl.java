package com.pickteer.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.pickteer.models.Form;
import com.pickteer.models.FormAnswer;
import com.pickteer.models.InputKeys;
import com.pickteer.models.LinearScaleQuestion;
import com.pickteer.models.MultipleChoiceQuestion;
import com.pickteer.models.Question;
import com.pickteer.models.QuestionType;
import com.pickteer.models.SingleChoiceQuestion;
import com.pickteer.models.TextAreaQuestion;
import com.pickteer.repository.FormRepository;

@Service
public class FormServiceImpl implements FormService {

	@Autowired
	private FormRepository formRepository;
	
	@Autowired
	private FormAnswerService formAnswerService;

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
					form.getQuestions().add(new LinearScaleQuestion(linearScaleQuestion, question.getQuestionText(), question.getDesiredValue()));

				} else if (!singleChoiceQuestion.isEmpty()) {
					form.getQuestions().add(new SingleChoiceQuestion(singleChoiceQuestion, question.getQuestionText(), question.getDesiredValue()));

				} else if (!multipleChoiceQuestion.isEmpty()) {
					form.getQuestions().add(new MultipleChoiceQuestion(multipleChoiceQuestion, question.getQuestionText(), question.getDesiredValue()));

				} else {
					form.getQuestions().add(new TextAreaQuestion(question.getQuestionText(), question.getDesiredValue()));

				}
			}
		});
		
		return form;
	}

	@Override
	public void checkAnswers(MultiValueMap<String, String> formData, long id) {
		Form form = getFormById(id);
		int points = 0;
		FormAnswer answer = new FormAnswer();
		
		for (Map.Entry<String, List<String>> entry : formData.entrySet()) {
			
			if(!entry.getKey().contains("csrf") && !entry.getKey().contains("id")) {
				answer.getAnswers().put(entry.getKey(), entry.getValue().toString());
			}
			
	        for(Question question : form.getQuestions()) {
	        	if(question.getQuestionText().trim().contains(entry.getKey().trim())) {
	        		if(question.getQuestionType().equals(QuestionType.MULTIPLE_CHOICE)) {
	        			points += ( calculatePoints(question, entry.getValue().toString()) * 100 / form.getQuestions().size());
	        		} else {
	        			points += ( calculatePoints(question, entry.getValue().get(0).trim()) * 100 / form.getQuestions().size());
	        		}
	        		break;
	        	}
	        }
	        
	        answer.setPoints(points);
	        answer.setUser(form.getUser());
	        answer.setFormName(form.getName());
	        answer.setFormId(id);
	        formAnswerService.add(answer);
	    }
		
		
		
	}

	private double calculatePoints(Question question, String givenAnswer){
		int answersCount = 0;
		
		switch (question.getQuestionType()) {
		case LINEAR_SCALE:
			double multiplier = 1 - Math.abs( (Integer.parseInt(question.getDesiredValue()) - Integer.parseInt(givenAnswer)) * 0.2);
			return multiplier > 0d ? multiplier : 0d;
			
		case MULTIPLE_CHOICE:
			String[] indexes = question.getDesiredValue().split(",");
			
			for (int i = 0; i < indexes.length; i++) {
				if(givenAnswer.contains(((MultipleChoiceQuestion)question).getAnswerList().get(Integer.parseInt(indexes[i]) - 1).trim())) {
					answersCount++;
				}
			}
			return answersCount / ((double)indexes.length);
			
		case SINGLE_CHOICE:
			if(((SingleChoiceQuestion)question).getAnswerList().get(Integer.parseInt(question.getDesiredValue()) - 1).trim().equals(givenAnswer)) {
				return 1;
			} else {
				return 0;
			}
				
			
		case TEXT_AREA:
			String[] words = question.getDesiredValue().split(",");
			
			for (int i = 0; i < words.length; i++) {
				if(givenAnswer.toLowerCase().contains(words[i].trim().toLowerCase())) {
					answersCount++;
				}
			}
			return answersCount / ((double)words.length);
		}
		
		return 0;
	}

}
