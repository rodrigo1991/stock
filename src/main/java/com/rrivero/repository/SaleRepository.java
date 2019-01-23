package com.rrivero.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rrivero.model.Sale;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

public interface SaleRepository extends JpaRepository<Sale, Long> {

}
