package com.rrivero.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rrivero.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
	

	@EntityGraph(attributePaths = {"user","user.perfil","branch"})
	Page<Assignment>findAll(Pageable pageable);
	
	@EntityGraph(attributePaths = {"user","user.perfil","branch"})
	Page<Assignment> findByUserId(@Param("userId")Long userId, Pageable pageable);
	
	@EntityGraph(attributePaths = {"user","user.perfil","branch"})
	Page<Assignment> findByBranchId(@Param("branchId")Long branchId, Pageable pageable);

	@EntityGraph(attributePaths = {"user","user.perfil","branch"})
	Page<Assignment> findByUserIdAndBranchId(@Param("userId")Long userId, @Param("branchId")Long branchId, Pageable pageable);
	
	@EntityGraph(attributePaths = {"branch","user","user.perfil"})
	Page<Assignment> findByBranchIdAndUserId(@Param("branchId")Long branchId, @Param("userId")Long userId, Pageable pageable);

}