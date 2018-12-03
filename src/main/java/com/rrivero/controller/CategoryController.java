package com.rrivero.controller;

import com.rrivero.model.Category;
import com.rrivero.repository.CategoryRepository;

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
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepository;
	/**
	 * Create a link to add an Category to an existing Perfil or create a new Perfil when
	 * the category views a specific Category.
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@GetMapping("/{id}")
	public Category getCategory(@PathVariable Long id) {
		
		Optional<Category> category = categoryRepository.findById(id);		
		return category.get();
	}
	
	@GetMapping()
	public Page<Category> getCategories(Pageable pageable) {
		
		Page<Category> categories = categoryRepository.findAll(pageable);		
		return categories;
	}
	
	
	@PostMapping()
	public Category create(@RequestBody Category category) {
		
		System.out.println("ejecutando create");		
		Category usuario = categoryRepository.save(category);	
		return usuario;
	}
	

}