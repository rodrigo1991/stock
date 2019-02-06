package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Assignment;
import com.rrivero.repository.AssignmentRepository;
import com.rrivero.repository.BranchRepository;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.Valid;

@RestController
@RequestMapping("/assignments")
public class AssignmentController {

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping()
	public Page<Assignment> getAllAssignments(Pageable pageable) {
		return this.assignmentRepository.findAll(pageable);
	}
	
	@GetMapping("{assignmentId}")
	public Assignment getAssignmentById(@PathVariable Long assignmentId, Pageable pageable) {
		return this.assignmentRepository.findById(assignmentId).orElseThrow(() -> new ResourceNotFoundException("Assignment " + assignmentId + " not found"));		
		
	}
	
	@PutMapping("{assignmentId}")
	public ResponseEntity<Assignment> updateAssignment(@PathVariable Long assignmentId,  @Valid @RequestBody Assignment assignmentRequest) {
		
		if (!this.branchRepository.existsById(assignmentRequest.getBranch().getId())) {
			throw new ResourceNotFoundException("Branch " + assignmentRequest.getBranch().getId() + " not found");
		}
		
		if (!this.userRepository.existsById(assignmentRequest.getUser().getId())) {
			throw new ResourceNotFoundException("User " + assignmentRequest.getUser().getId() + " not found");
		}
		
		return this.assignmentRepository.findById(assignmentId).map(assignment -> {
			assignment.setBegin(assignmentRequest.getBegin());
			assignment.setEnd(assignmentRequest.getEnd());
			assignment.setUser(assignmentRequest.getUser());
			assignment.setBranch(assignmentRequest.getBranch());
			Assignment savedAssignment = this.assignmentRepository.save(assignment);
			return ResponseEntity.ok(savedAssignment);
		}).orElseThrow(() -> new ResourceNotFoundException("assignmentId " + assignmentId + " not found"));
	}
	
	@PostMapping()
	public ResponseEntity<Assignment> create(@RequestBody Assignment assignment) {
		Assignment savedAssignment= this.assignmentRepository.save(assignment);	
		return new ResponseEntity<Assignment>(savedAssignment, HttpStatus.CREATED);
	}

	
	@DeleteMapping("{assignmentId}")
	public ResponseEntity<?> deleteComment(@PathVariable Long assignmentId) {
		Assignment assignment = this.assignmentRepository.findById(assignmentId)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment " + assignmentId + " not found"));
		this.assignmentRepository.delete(assignment);
		return ResponseEntity.noContent().build();
	}
    
}
