package com.pickteer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickteer.models.Form;
import com.pickteer.models.FormAnswer;

@Repository
public interface FormAnswerRepository extends JpaRepository < FormAnswer, Long > {
	public List<FormAnswer> findByUserId(long userId);
	public List<FormAnswer> findByFormId(long formId);
}
