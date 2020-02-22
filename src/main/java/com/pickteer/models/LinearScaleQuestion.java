package com.pickteer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LinearScaleQuestion")
public class LinearScaleQuestion extends Question {

	private int lowValue;
	private int highValue;
	private String lowLabel;
	private String highLabel;
	
	public LinearScaleQuestion() { setQuestionType(QuestionType.LINEAR_SCALE);}

	public LinearScaleQuestion(String questionText, String desiredValue, int lowValue, int highValue, String lowLabel, String highLabel) {
		super(questionText, QuestionType.LINEAR_SCALE, desiredValue);
		this.lowValue = lowValue;
		this.highValue = highValue;
		this.lowLabel = lowLabel;
		this.highLabel = highLabel;
	}

	public int getLowValue() {
		return lowValue;
	}

	public void setLowValue(int lowValue) {
		this.lowValue = lowValue;
	}

	public int getHighValue() {
		return highValue;
	}

	public void setHighValue(int highValue) {
		this.highValue = highValue;
	}

	public String getLowLabel() {
		return lowLabel;
	}

	public void setLowLabel(String lowLabel) {
		this.lowLabel = lowLabel;
	}

	public String getHighLabel() {
		return highLabel;
	}

	public void setHighLabel(String highLabel) {
		this.highLabel = highLabel;
	}
	
	public boolean isEmpty() {
		return highValue == 0;
	}
	
	public void reset() {
		lowValue = 0;
		highValue = 0;
		lowLabel = "";
		highLabel = "";
	}
}
