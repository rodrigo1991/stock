package com.rrivero.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rrivero.model.Branch;
import com.rrivero.model.Product;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
