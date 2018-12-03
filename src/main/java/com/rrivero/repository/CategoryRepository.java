package com.rrivero.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rrivero.model.Category;
import com.rrivero.model.Product;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
