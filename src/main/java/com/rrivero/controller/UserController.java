package com.rrivero.controller;

import com.rrivero.model.User;
import com.rrivero.model.User;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	/**
	 * Create a link to add an User to an existing Perfil or create a new Perfil when
	 * the user views a specific User.
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		
		Optional<User> user = userRepository.findById(id);		
		return user.get();
	}
	
	@GetMapping()
	public Page<User> getUsers(Pageable pageable) {
		
		Page<User> users = userRepository.findAll(pageable);		
		return users;
	}
	
	
	@PutMapping("/{id}")
	public User update(@RequestBody User user, @PathVariable long id) {
		
		user.setId(id);	
		User savedUser = userRepository.save(user);	
		return savedUser;
	}
	
	
	@PostMapping()
	public User create(@RequestBody User user) {
		
		System.out.println("ejecutando create");		
		User usuario = userRepository.save(user);	
		return usuario;
	}
	

}