package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Perfil;
import com.rrivero.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/perfils")
public class PerfilController {

	@Autowired
	private PerfilRepository perfilRepository;
	
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
	
	
	@PostMapping()
	public Perfil create(@RequestBody Perfil perfil) {
		Perfil usuario = perfilRepository.save(perfil);	
		return usuario;
	}
	

}