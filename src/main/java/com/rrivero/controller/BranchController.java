package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Branch;
import com.rrivero.model.Assignment;
import com.rrivero.repository.AssignmentRepository;
import com.rrivero.repository.BranchRepository;
import com.rrivero.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import javax.validation.Valid;

@RestController
@RequestMapping("/branches")
public class BranchController {

	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private AssignmentRepository assignmentRepository;
	
	@Autowired
	private UserRepository userRepository;

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
	
	@GetMapping("/{branchId}/assignments")
	public Page<Assignment> getAllAssignmentsByBranchId(@PathVariable Long branchId, Pageable pageable) {
		return this.assignmentRepository.findByBranchId(branchId, pageable);
	}


	@PostMapping()
	public ResponseEntity<Branch> create(@RequestBody Branch branch) {
		Branch savedBranch = branchRepository.save(branch);
		return new ResponseEntity<Branch>(savedBranch, HttpStatus.CREATED);
	}
	
	
	@GetMapping("/{branchId}/assignments/users/{userId}")
	public Page<Assignment> getAllAssignmentsByUserIdAndBranchId(@PathVariable Long branchId, @PathVariable Long userId, Pageable pageable) {
		return this.assignmentRepository.findByBranchIdAndUserId(branchId, userId , pageable);
	}
	
	@PostMapping("/{branchId}/assignments/users/{userId}")
	public ResponseEntity<Assignment> createAssignments(@PathVariable Long branchId, @PathVariable Long userId, @Valid @RequestBody Assignment assignment) {
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