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

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/assignments")
	public Page<Assignment> getAllAssignments(Pageable pageable) {
		return this.assignmentRepository.findAll(pageable);
	}
	
	@GetMapping("/assignments/{id}")
	public Assignment getAssignmentById(@PathVariable Long id, Pageable pageable) {
		return this.assignmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Assignment " + id + " not found"));		
		
	}

	@GetMapping("/users/{userId}/assignments")
	public Page<Assignment> getAllAssignmentsByUserId(@PathVariable(value = "userId") Long userId, Pageable pageable) {
		return this.assignmentRepository.findByUserId(userId, pageable);
	}

	@PostMapping("/users/{userId}/assignments")
	public Assignment createAssignment(@PathVariable(value = "userId") Long userId, @Valid @RequestBody Assignment assignment) {
		return userRepository.findById(userId).map(user -> {
			assignment.setUser(user);
			return this.assignmentRepository.save(assignment);
		}).orElseThrow(() -> new ResourceNotFoundException("UserId " + userId + " not found"));
	}

	@PutMapping("/users/{userId}/assignments/{id}")
	public Assignment updateAssignment(@PathVariable Long userId,	@PathVariable Long id, @Valid @RequestBody Assignment assignmentRequest) {
		if (!userRepository.existsById(userId)) {
			throw new ResourceNotFoundException("UserId " + userId + " not found");
		}

		return this.assignmentRepository.findById(id).map(assignment -> {
			assignment.setUser(assignmentRequest.getUser());
			return this.assignmentRepository.save(assignment);
		}).orElseThrow(() -> new ResourceNotFoundException("AssignmentId " + id + "not found"));
	}

	
	@DeleteMapping("/assignments/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		Assignment assignment = this.assignmentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Assignment " + id + " not found"));
		this.assignmentRepository.delete(assignment);
		return ResponseEntity.ok().build();
	}
    
}