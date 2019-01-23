package com.rrivero.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rrivero.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@EntityGraph(attributePaths = {"category"})
	Page<Product> findAll(Pageable pageable);
}
