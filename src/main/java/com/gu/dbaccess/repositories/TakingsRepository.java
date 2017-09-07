package com.gu.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.TakeEntity;

public interface TakingsRepository extends CrudRepository<TakeEntity, Long> {

	public TakeEntity findFirstByOrderByIdtakeDesc();

}
