package com.sboot.golden.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.IncidentEntity;

public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

	Iterable<IncidentEntity> findByState(Boolean state);
}
