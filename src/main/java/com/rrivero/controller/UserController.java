package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Assignment;
import com.rrivero.model.Perfil;
import com.rrivero.model.User;
import com.rrivero.repository.AssignmentRepository;
import com.rrivero.repository.BranchRepository;
import com.rrivero.repository.UserRepository;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private BranchRepository branchRepository;
	
	@GetMapping()
	public Page<User> getAllUsers(Pageable pageable) {
		return this.userRepository.findAll(pageable);
	}
	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id, Pageable pageable) {
		return this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));		
		
	}

	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteComment(@PathVariable Long id) {
		User user = this.userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User " + id + " not found"));
		this.userRepository.delete(user);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{userId}/assignments")
	public Page<Assignment> getAllAssignmentsByUserId(@PathVariable Long userId, Pageable pageable) {
		return this.assignmentRepository.findByUserId(userId, pageable);
	}
	
	@GetMapping("/{userId}/assignments/branches/{branchId}")
	public Page<Assignment> getAllAssignmentsByUserIdAndBranchId(@PathVariable Long userId, @PathVariable Long branchId, Pageable pageable) {
		return this.assignmentRepository.findByUserIdAndBranchId(userId, branchId, pageable);
	}
	
	@PostMapping("/{userId}/assignments/branches/{branchId}")
	public ResponseEntity<Assignment> createAssignments(@PathVariable Long userId, @PathVariable Long branchId, @Valid @RequestBody Assignment assignment) {
		return this.userRepository.findById(userId).map(user -> {
			assignment.setUser(user);
			
			this.branchRepository.findById(branchId).map(branch -> {
				assignment.setBranch(branch);				
				return this.assignmentRepository.save(assignment);
			}).orElseThrow(() -> new ResourceNotFoundException("branchId " + branchId + " not found"));
			
			Assignment savedAssignment = this.assignmentRepository.save(assignment);
			return new ResponseEntity<Assignment>(savedAssignment, HttpStatus.CREATED);
		}).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
	}
}
