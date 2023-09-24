package com.atmj.gsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.IncidentEntity;

public interface IncidentRepository extends CrudRepository<IncidentEntity, Long> {

	Iterable<IncidentEntity> findByState(Boolean state);
}
