package com.rrivero.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.rrivero.model.User;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	
	@RestResource(exported = true)
	Page <User>findByName(@Param("name")String name, Pageable pageable);
	@RestResource(exported = true)
	Page<User> findBySurname(@Param("surname")String surname, Pageable pageable);	
	
}
