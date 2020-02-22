package com.pickteer.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "SingleChoiceQuestion")
public class SingleChoiceQuestion extends Question {

	@ElementCollection
	private List<String> answerList;
	
	public SingleChoiceQuestion() { setQuestionType(QuestionType.SINGLE_CHOICE); answerList = new ArrayList<>();}

	public SingleChoiceQuestion(String questionText, String desiredValue, List<String> answerList) {
		super(questionText, QuestionType.SINGLE_CHOICE, desiredValue);
		this.answerList = answerList;
	}

	public List<String> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<String> answerList) {
		this.answerList = answerList;
	}
	
	public boolean isEmpty() {
		return answerList.isEmpty();
	}
	
	@Override
	public void reset() {
		answerList.clear();
	}

}
