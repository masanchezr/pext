package com.bar.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bar.dbaccess.entities.IncidentEntity;
import com.bar.dbaccess.entities.UserEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface IncidentRepository.
 */
public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

	/**
	 * Find by username.
	 *
	 * @param user the user
	 * @return the iterable
	 */
	Iterable<IncidentEntity> findByUsername(UserEntity user);

	/**
	 * Find by state.
	 *
	 * @param state the state
	 * @return the iterable
	 */
	Iterable<IncidentEntity> findByState(Boolean state);
}
