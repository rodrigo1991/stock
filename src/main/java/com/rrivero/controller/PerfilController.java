package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Branch;
import com.rrivero.model.Perfil;
import com.rrivero.model.User;
import com.rrivero.repository.PerfilRepository;
import com.rrivero.repository.UserRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfils")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public Perfil getPerfil(@PathVariable Long id) {
		
		return this.perfilRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Perfil " + id + " not found"));	
	}	
	
	@GetMapping()
	public Page<Perfil> getPerfil(Pageable pageable) {
		
		Page<Perfil> perfils = perfilRepository.findAll(pageable);		
		return perfils;
	}
	
	
	@GetMapping("/{perfilId}/users")
	public Page<User> getAllUsersByPerfilId(@PathVariable Long perfilId, Pageable pageable) {
		return this.userRepository.findByPerfilId(perfilId, pageable);
	}

	@PostMapping("/{perfilId}/users")
	public User createUser(@PathVariable Long perfilId, @Valid @RequestBody User user) {
		return perfilRepository.findById(perfilId).map(perfil -> {
			user.setPerfil(perfil);
			return this.userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("PerfilId " + perfilId + " not found"));
	}

	@PutMapping("/{perfilId}/users/{id}")
	public User updateUser(@PathVariable Long perfilId,	@PathVariable Long id, @Valid @RequestBody User userRequest) {
		if (!perfilRepository.existsById(perfilId)) {
			throw new ResourceNotFoundException("PerfilId " + perfilId + " not found");
		}

		return this.userRepository.findById(id).map(user -> {
			user.setName(userRequest.getName());
			return this.userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId " + id + "not found"));
	}
	
	@PostMapping()
	public ResponseEntity<Perfil> create(@RequestBody Perfil perfil) {
		Perfil savedPerfil= perfilRepository.save(perfil);	
		return new ResponseEntity<Perfil>(savedPerfil, HttpStatus.CREATED);
	}
	
	/**
	 * Student savedStudent = studentRepository.save(student);

	URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			.buildAndExpand(savedStudent.getId()).toUri();

	return ResponseEntity.created(location).build();
	 */
	

}