package com.rrivero.controller;

import com.rrivero.model.Product;
import com.rrivero.model.Product;
import com.rrivero.repository.ProductRepository;

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
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	/**
	 * Create a link to add an Product to an existing Perfil or create a new Perfil when
	 * the product views a specific Product.
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable Long id) {
		
		Optional<Product> product = productRepository.findById(id);		
		return product.get();
	}
	
	@GetMapping()
	public Page<Product> getProducts(Pageable pageable) {
		
		Page<Product> products = productRepository.findAll(pageable);		
		return products;
	}
	
	@PutMapping("/{id}")
	public Product update(@RequestBody Product product, @PathVariable long id) {
		
		product.setId(id);	
		Product savedProduct = productRepository.save(product);	
		return savedProduct;
	}
	
	
	@PostMapping()
	public Product create(@RequestBody Product product) {
		
		System.out.println("ejecutando create");		
		Product usuario = productRepository.save(product);	
		return usuario;
	}
	

}