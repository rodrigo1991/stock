package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Assignment;
import com.rrivero.repository.AssignmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

	@Autowired
	private AssignmentRepository assignmentRepository;

	
	@GetMapping()
	public Page<Assignment> getAllAssignments(Pageable pageable) {
		return this.assignmentRepository.findAll(pageable);
	}
	
	@GetMapping("{assignmentId}")
	public Assignment getAssignmentById(@PathVariable Long assignmentId, Pageable pageable) {
		return this.assignmentRepository.findById(assignmentId).orElseThrow(() -> new ResourceNotFoundException("Assignment " + assignmentId + " not found"));		
		
	}
	
	@PutMapping("{assignmentId}")
	public ResponseEntity<?> updateAssignment(@PathVariable Long assignmentId,  @Valid @RequestBody Assignment assignmentRequest) {
		
		return this.assignmentRepository.findById(assignmentId).map(assignment -> {
			assignment.setBegin(assignmentRequest.getBegin());
			assignment.setEnd(assignmentRequest.getEnd());
			this.assignmentRepository.save(assignment);
			return ResponseEntity.noContent().build();
		}).orElseThrow(() -> new ResourceNotFoundException("assignmentId " + assignmentId + " not found"));
	}

	
	@DeleteMapping("{assignmentId}")
	public ResponseEntity<?> deleteComment(@PathVariable Long assignmentId) {
		Assignment assignment = this.assignmentRepository.findById(assignmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment " + assignmentId + " not found"));
		this.assignmentRepository.delete(assignment);
		return ResponseEntity.noContent().build();
	}
    
}
