package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.User;
import com.rrivero.repository.PerfilRepository;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PerfilRepository perfilRepository;
	
	@GetMapping("/users")
	public Page<User> getAllUsers(Pageable pageable) {
		return userRepository.findAll(pageable);
	}
	
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable Long id, Pageable pageable) {
		Optional<User> user = userRepository.findById(id);		
		return user.get();
	}

	@GetMapping("/perfils/{perfilId}/users")
	public Page<User> getAllUsersByPerfilId(@PathVariable(value = "perfilId") Long perfilId, Pageable pageable) {
		return userRepository.findByPerfilId(perfilId, pageable);
	}

	@PostMapping("/perfils/{perfilId}/users")
	public User createUser(@PathVariable(value = "perfilId") Long perfilId, @Valid @RequestBody User user) {
		return perfilRepository.findById(perfilId).map(perfil -> {
			user.setPerfil(perfil);
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("PerfilId " + perfilId + " not found"));
	}

	@PutMapping("/perfils/{perfilId}/users/{userId}")
	public User updateUser(@PathVariable Long perfilId,	@PathVariable Long userId, @Valid @RequestBody User userRequest) {
		if (!perfilRepository.existsById(perfilId)) {
			throw new ResourceNotFoundException("PerfilId " + perfilId + " not found");
		}

		return userRepository.findById(userId).map(user -> {
			user.setName(userRequest.getName());
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + "not found"));
	}

	/*
	 @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable (value = "postId") Long postId, @PathVariable (value = "commentId") Long commentId) {
        return userRepository.findByIdAndPerfilId(commentId, postId).map(comment -> {
        	userRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }
    */
}
