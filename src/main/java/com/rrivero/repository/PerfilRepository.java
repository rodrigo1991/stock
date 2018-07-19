package com.rrivero.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.rrivero.model.Perfil;
/**
 * Created by rajeevkumarsingh on 27/06/17.
 */

@RepositoryRestResource
public interface PerfilRepository extends JpaRepository<Perfil, Long> {

}
