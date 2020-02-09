package com.pickteer.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SingleChoiceQuestion")
public class SingleChoiceQuestion extends Question {

	private List<String> answerList;
	
	public SingleChoiceQuestion() {}

	public SingleChoiceQuestion(String questionText, String questionType, String desiredValue, List<String> answerList) {
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
