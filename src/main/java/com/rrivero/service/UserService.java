package com.rrivero.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.rrivero.model.User;

public interface UserService {

	List<User> getUsersByName	(String name,	PageRequest pageRequest);
	Page<User> getUsersBySurname(String surname,Pageable pageable);

}
