package com.rrivero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rrivero.model.User;
import com.rrivero.service.UserService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@RestController
public class UserController {

	
	@Autowired
	private UserService userService;	

    @RequestMapping(value = "/users/name/{name}", method = RequestMethod.GET)
    public List<User> getUsersByName(@PathVariable("name") String name) {
		return userService.getUsersByName(name, PageRequest.of(0, 2, new Sort(Direction.ASC, "name")));
	}
    //http://127.0.0.1:8080/users/surname/diaz?page=0&size=2&sort=name,desc
    @RequestMapping(value = "/users/surname/{surname}", method = RequestMethod.GET)
    public Page<User> getUsersBySurName(@PathVariable("surname") String surname, Pageable pageable) {
    	
    	return userService.getUsersBySurname(surname,pageable);
	}
}
