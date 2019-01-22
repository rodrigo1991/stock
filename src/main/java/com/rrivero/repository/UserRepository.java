package com.rrivero.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.rrivero.model.User;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
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
	
	

	/**
	
	@RestResource(path = "byMaxPrice")
	@Query("SELECT i FROM User i WHERE i.price <= :maxPrice")
	List<User> findUsersLessThan(@Param("maxPrice") double maxPrice);

	/**
	 * An example of allowing users to search for within a resource using using
	 * multiple search criteria.
	 * 
	 * Allow users to search by the maximum price of an item and its type.
	 * 
	 * @param maxPrice
	 * @param type
	 * @return
	 
	@RestResource(path = "byMaxPriceAndType")
	@Query("SELECT i FROM User i WHERE i.price <= :maxPrice AND i.type = :type")
	List<User> findUsersLessThanAndType(@Param("maxPrice") double maxPrice, @Param("type") String type);

	*/
}
