package com.rrivero.controller;

import com.rrivero.model.Branch;
import com.rrivero.repository.BranchRepository;

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
@RequestMapping("/branches")
public class BranchController {

	@Autowired
	private BranchRepository branchRepository;
	/**
	 * Create a link to add an Branch to an existing Perfil or create a new Perfil when
	 * the branch views a specific Branch.
	 * 
	 * @param id
	 * @return 
	 * @return
	 */
	@GetMapping("/{id}")
	public Branch getBranch(@PathVariable Long id) {
		
		Optional<Branch> branch = branchRepository.findById(id);		
		return branch.get();
	}
	
	@GetMapping()
	public Page<Branch> getBranches(Pageable pageable) {
		
		Page<Branch> branches = branchRepository.findAll(pageable);		
		return branches;
	}
	
	
	@PostMapping()
	public Branch create(@RequestBody Branch branch) {
		
		System.out.println("ejecutando create");		
		Branch usuario = branchRepository.save(branch);	
		return usuario;
	}
	

}