package com.gu.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.TimeEntity;

public interface TimeRepository extends CrudRepository<TimeEntity, Long> {

	public List<TimeEntity> findByActiveTrueOrderByOrder();

	public List<TimeEntity> findByOrderByOrder();

}
