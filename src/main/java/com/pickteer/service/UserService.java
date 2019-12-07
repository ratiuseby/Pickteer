package com.pickteer.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.pickteer.models.User;

public interface UserService extends UserDetailsService {

    User findByUsername(String username);
}