package com.pickteer.models;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "descriminatorColumn")
@Table(name = "Question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	public Long getId() {
		return id;
	}

	private String questionText;
	private QuestionType questionType;
	private String desiredValue;

	public Question() {}

	public Question(String questionText, QuestionType questionType, String desiredValue) {
		super();
		this.questionText = questionText;
		this.questionType = questionType;
		this.desiredValue = desiredValue;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public QuestionType getQuestionType() {
		return questionType;
	}

	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}

	public String getDesiredValue() {
		return desiredValue;
	}

	public void setDesiredValue(String desiredValue) {
		this.desiredValue = desiredValue;
	}
	
	public void reset() {
		questionText = "";
		desiredValue = "";
	}

	@Override
	public String toString() {
		return "Question [questionText=" + questionText + ", questionType=" + questionType
				+ ", desiredValue=" + desiredValue + "]";
	}
}
