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

	public SingleChoiceQuestion(SingleChoiceQuestion singleChoiceQuestion) {
		this.answerList = singleChoiceQuestion.answerList;
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
		super.reset();
		answerList.clear();
	}

}
