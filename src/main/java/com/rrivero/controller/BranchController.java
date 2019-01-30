package com.rrivero.controller;

import com.rrivero.exception.ResourceNotFoundException;
import com.rrivero.model.Branch;
import com.rrivero.model.Assignment;
import com.rrivero.repository.AssignmentRepository;
import com.rrivero.repository.BranchRepository;

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

	@PostMapping("/{branchId}/assignments")
	public Assignment createAssignment(@PathVariable Long branchId, @Valid @RequestBody Assignment assignment) {
		return branchRepository.findById(branchId).map(branch -> {
			assignment.setBranch(branch);
			return this.assignmentRepository.save(assignment);
		}).orElseThrow(() -> new ResourceNotFoundException("BranchId " + branchId + " not found"));
	}

	/*
	@PutMapping("/{branchId}/assignments/{id}")
	public Assignment updateAssignment(@PathVariable Long branchId,	@PathVariable Long id, @Valid @RequestBody Assignment assignmentRequest) {
		if (!branchRepository.existsById(branchId)) {
			throw new ResourceNotFoundException("BranchId " + branchId + " not found");
		}

		return this.assignmentRepository.findById(id).map(assignment -> {
			assignment.setName(assignmentRequest.getName());
			return this.assignmentRepository.save(assignment);
		}).orElseThrow(() -> new ResourceNotFoundException("AssignmentId " + id + "not found"));
	}
	*/

	@PostMapping()
	public ResponseEntity<Branch> create(@RequestBody Branch branch) {
		Branch savedBranch = branchRepository.save(branch);
		return new ResponseEntity<Branch>(savedBranch, HttpStatus.CREATED);
	}

}