package com.pickteer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickteer.models.Form;

@Repository
public interface FormRepository extends JpaRepository < Form, Long > {
	public List<Form> findByUserId(long userId);
}
