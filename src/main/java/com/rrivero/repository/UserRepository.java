package com.rrivero.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rrivero.model.User;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	
	List <User>findByName(String name, Pageable pageable);
	Page<User> findBySurname(String surname, Pageable pageable);
	
}
