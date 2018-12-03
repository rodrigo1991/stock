package com.rrivero.controller;

import com.rrivero.model.Perfil;
import com.rrivero.model.User;
import com.rrivero.repository.PerfilRepository;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/perfils")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	
	@Autowired
	private UserRepository userRepository;
	/**
	 * Create a link to add an Perfil to an existing Perfil or create a new Perfil when
	 * the perfil views a specific Perfil.
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@GetMapping("/{id}")
	public Perfil getPerfil(@PathVariable Long id) {
		
		Optional<Perfil> perfil = perfilRepository.findById(id);		
		return perfil.get();
	}	
	
	@GetMapping("/{id}/users")
	public Page<User> getUsersByPerfil(@PathVariable Long id, Pageable pageable) {
		
		Page<User> users = userRepository.findByPerfilId(id, pageable);		
		return users;
	}	
	
	
	@GetMapping()
	public Page<Perfil> getPerfil(Pageable pageable) {
		
		Page<Perfil> perfils = perfilRepository.findAll(pageable);		
		return perfils;
	}
	
	
	@PostMapping()
	public Perfil create(@RequestBody Perfil perfil) {
		
		System.out.println("ejecutando create");		
		Perfil usuario = perfilRepository.save(perfil);	
		return usuario;
	}
	

}