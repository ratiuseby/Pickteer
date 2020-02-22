package com.pickteer.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TextAreaQuestion")
public class TextAreaQuestion extends Question {

	public TextAreaQuestion() { setQuestionType(QuestionType.TEXT_AREA);}

}
