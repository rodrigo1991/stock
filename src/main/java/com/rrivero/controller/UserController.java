package com.rrivero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;

import com.rrivero.model.User;
import com.rrivero.repository.UserRepository;

import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;

@BasePathAwareController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	
	@DeleteMapping("users/{id}")
	public ResponseEntity<User> borrar(@PathVariable final long id) {
		System.out.println("overriding delete "+id);
		return null;
	}

	
	@PostMapping("users")
	public ResponseEntity<Resource<User>> viewUser(@RequestBody User user) {
	  System.out.println(user);
	  return null;
	}
	
	
	@GetMapping("users/login")
	public ResponseEntity<User> login() {
		System.out.println("login");
		return null;
	}
	
	@GetMapping("users/na/{id}")
	public ResponseEntity<User> get(@PathVariable final long id) {
		System.out.println("metodo propio "+id);
		return null;
	}
	
	
	@GetMapping("users/propio/{propio}")
	public ResponseEntity<User> propio(@PathVariable final String propio) {
		System.out.println(propio);
		return null;
	}
	

}
