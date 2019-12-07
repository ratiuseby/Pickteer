package com.pickteer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pickteer.models.User;

@Repository
public interface UserRepository extends JpaRepository < User, Long > {
    User findByUsername(String email);
}