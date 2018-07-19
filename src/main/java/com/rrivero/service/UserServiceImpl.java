package com.rrivero.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rrivero.model.User;
import com.rrivero.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public List<User> getUsersByName(String name, PageRequest pageRequest) {
		if (name.length() > 20 || name.length() < 1) {
			return new ArrayList<User>();
		} else {
			return userRepository.findByName(name, pageRequest);
		}
	}

	@Override
	public Page<User> getUsersBySurname(String surname, Pageable pageable) {
		if (surname.length() > 20 || surname.length() < 1) {
			return null;
		} else {
			return userRepository.findBySurname(surname, pageable);
		}
	}

}
