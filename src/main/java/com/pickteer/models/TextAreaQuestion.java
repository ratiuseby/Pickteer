package com.pickteer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MultipleChoiceQuestion")
public class TextAreaQuestion extends Question {

	private String answer;
	
	public TextAreaQuestion() {}

	public TextAreaQuestion(String questionText, String questionType, String desiredValue, String answer) {
		super(questionText, questionType, desiredValue);
		this.answer = answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
