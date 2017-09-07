package com.gu.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.IncidentEntity;

public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

	Iterable<IncidentEntity> findByState(Boolean state);
}
