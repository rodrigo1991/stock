package com.rrivero.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rrivero.model.User;

public interface UserService {

	Page<User> getUsersByName	(String name,	Pageable pageable);
	Page<User> getUsersBySurname(String surname,Pageable pageable);

}
