package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.User;
import com.rrivero.repository.PerfilRepository;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping("/users")
	public Page<User> getAllUsers(Pageable pageable) {
		return this.userRepository.findAll(pageable);
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id, Pageable pageable) {
		return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));		
		
	}

	@GetMapping("/perfils/{perfilId}/users")
	public Page<User> getAllUsersByPerfilId(@PathVariable(value = "perfilId") Long perfilId, Pageable pageable) {
		return this.userRepository.findByPerfilId(perfilId, pageable);
	}

	@PostMapping("/perfils/{perfilId}/users")
	public User createUser(@PathVariable(value = "perfilId") Long perfilId, @Valid @RequestBody User user) {
		return perfilRepository.findById(perfilId).map(perfil -> {
			user.setPerfil(perfil);
			return this.userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("PerfilId " + perfilId + " not found"));
	}

	@PutMapping("/perfils/{perfilId}/users/{id}")
	public User updateUser(@PathVariable Long perfilId,	@PathVariable Long id, @Valid @RequestBody User userRequest) {
		if (!perfilRepository.existsById(perfilId)) {
			throw new ResourceNotFoundException("PerfilId " + perfilId + " not found");
		}

		return this.userRepository.findById(id).map(user -> {
			user.setName(userRequest.getName());
			return this.userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + "not found"));
	}

	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
		this.userRepository.delete(user);
		return ResponseEntity.ok().build();
	}
    
}
