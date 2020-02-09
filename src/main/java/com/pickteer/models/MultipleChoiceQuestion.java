package com.pickteer.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "MultipleChoiceQuestion")
public class MultipleChoiceQuestion extends Question {

	private List<String> answerList;
	
	public MultipleChoiceQuestion() {}
	
	public MultipleChoiceQuestion(String questionText, String questionType, String desiredValue, List<String> answerList) {
		super(questionText, questionType, desiredValue);
		this.answerList = answerList;
	}
	
	public List<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}

}
