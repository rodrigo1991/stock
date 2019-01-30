package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Assignment;
import com.rrivero.repository.UserRepository;
import com.rrivero.repository.AssignmentRepository;

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
public class AssignmentsController {

	@Autowired
	private AssignmentRepository assignmentRepository;

	
	@GetMapping("/assignments")
	public Page<Assignment> getAllAssignments(Pageable pageable) {
		return this.assignmentRepository.findAll(pageable);
	}
	
	@GetMapping("/assignments/{id}")
	public Assignment getAssignmentById(@PathVariable Long id, Pageable pageable) {
		return this.assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment " + id + " not found"));		
		
	}

	
	@DeleteMapping("/assignments/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		Assignment assignment = this.assignmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment " + id + " not found"));
		this.assignmentRepository.delete(assignment);
		return ResponseEntity.ok().build();
	}
    
}
