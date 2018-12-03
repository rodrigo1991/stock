package com.rrivero.controller;

import com.rrivero.model.Sale;
import com.rrivero.repository.SaleRepository;

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
@RequestMapping("/sales")
public class SaleController {

	@Autowired
	private SaleRepository saleRepository;
	/**
	 * Create a link to add an Sale to an existing Perfil or create a new Perfil when
	 * the sale views a specific Sale.
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@GetMapping("/{id}")
	public Sale getSale(@PathVariable Long id) {
		
		Optional<Sale> sale = saleRepository.findById(id);		
		return sale.get();
	}
	
	@GetMapping()
	public Page<Sale> getSales(Pageable pageable) {
		
		Page<Sale> sales = saleRepository.findAll(pageable);		
		return sales;
	}
	
	
	@PutMapping("/{id}")
	public Sale update(@RequestBody Sale sale, @PathVariable long id) {
		
		sale.setId(id);	
		Sale savedSale = saleRepository.save(sale);	
		return savedSale;
	}
	
	
	@PostMapping()
	public Sale create(@RequestBody Sale sale) {
		
		System.out.println("ejecutando create");		
		Sale savedSale = saleRepository.save(sale);	
		return savedSale;
	}
	

}