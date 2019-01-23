package com.rrivero.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rrivero.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

	@EntityGraph(attributePaths = "perfil")
	Page<User>findAll(Pageable pageable);
	Page<User>findByName(@Param("name")String name, Pageable pageable);
	Page<User> findBySurname(@Param("surname")String surname, Pageable pageable);
	@EntityGraph(attributePaths = "perfil")
	Page<User> findByPerfilId(Long id, Pageable pageable);
	@EntityGraph(attributePaths = "perfil")
	Page<User> findByIdAndPerfilId(Long id, Long perfilId, Pageable pageable);

}
