package com.rrivero.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import com.rrivero.model.User;

/**
 * Created by rajeevkumarsingh on 27/06/17.
 */
@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
	

	Page <User>findByName(@Param("name")String name, Pageable pageable);
	Page<User> findBySurname(@Param("surname")String surname, Pageable pageable);
	
	

	/**
	 * Users should not be allowed to by deleted over the web.
	 */
	@RestResource(exported = false)
	void delete(User item);

	@RestResource(exported = false)
	void deleteAll();

	/**
	 * Only administrators should be able to create/make changes to an User.
	 */
	//@PreAuthorize("hasRole('ADMIN')")
	<S extends User> S save(S entity);

	//@PreAuthorize("hasRole('ADMIN')")
	<S extends User> Iterable<S> save(Iterable<S> entities);

	/**
	 * An example of allowing users to search within a resource by field.
	 * 
	 * Allow users to search for an item by its type.
	 * 
	 * @param type
	 * @return
	 */
//	List<User> findByType(@Param("type") String type);

	/**
	 * An example of allowing users to search for within a resource using a
	 * custom query and custom endpoint.
	 * 
	 * Allow users to search by the maximum price of an item.
	 * 
	 * @param maxPrice
	 * @return
	 
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
