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
	

	@EntityGraph(attributePaths = {"user","branch"})
	Page<Assignment>findAll(Pageable pageable);
	Page<Assignment>findByName(@Param("name")String name, Pageable pageable);
	Page<Assignment> findBySurname(@Param("surname")String surname, Pageable pageable);
	@EntityGraph(attributePaths = {"user","branch"})
	Page<Assignment> findByPerfilId(Long id, Pageable pageable);
	@EntityGraph(attributePaths = {"user","branch"})
	Page<Assignment> findByIdAndPerfilId(Long id, Long perfilId, Pageable pageable);
	Page<Assignment> findByUserId(Long userId, Pageable pageable);

}
