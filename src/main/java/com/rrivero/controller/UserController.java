package com.rrivero.controller;

import com.rrivero.model.Branch;
import com.rrivero.model.Perfil;
import com.rrivero.model.User;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RepositoryRestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RepositoryEntityLinks entityLinks;

	/**
	 * Create a link to add an User to an existing Perfil or create a new Perfil when
	 * the user views a specific User.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/")
	public ResponseEntity<Resource<User>> viewItem(@PathVariable String id) {
		User user = userRepository.getOne(Long.valueOf(id));

		Resource<User> resource = new Resource<User>(user);
		if (hasExistingPerfil()) {
			// Provide a link to an existing Perfil
			resource.add(entityLinks.linkToSingleResource(retrieveExistingPerfil()).withRel("addToCart"));
		} else {
			// Provide a link to create a new Perfil
			resource.add(entityLinks.linkFor(Perfil.class).withRel("addToCart"));
		}
		resource.add(entityLinks.linkToSingleResource(user).withSelfRel());
		
		System.out.println("sobreescrito");
		return ResponseEntity.ok(resource);
	}
	
	
	@PostMapping("/")
	public ResponseEntity<Resource<User>> create(@RequestBody Resource<User> user) {
		
		System.out.println("ejecutando create");
		
		User usuario = userRepository.save(user.getContent());
		Resource<User> resource = new Resource<User>(usuario);
		if (hasExistingPerfil()) {
			// Provide a link to an existing Perfil
			resource.add(entityLinks.linkToSingleResource(retrieveExistingPerfil()).withRel("addToCart"));
		} else {
			// Provide a link to create a new Perfil
			resource.add(entityLinks.linkFor(Perfil.class).withRel("addToCart"));
		}
		resource.add(entityLinks.linkToSingleResource(usuario).withSelfRel());
		
		System.out.println("sobreescrito");
		return ResponseEntity.ok(resource);
	}

	/**
	 * Retrieve an existing order.
	 * 
	 * @return
	 */
	private Identifiable<?> retrieveExistingPerfil() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Logic to determine if an user has an existing order.
	 * 
	 * @return
	 */
	private boolean hasExistingPerfil() {
		// TODO Auto-generated method stub
		return false;
	}

}