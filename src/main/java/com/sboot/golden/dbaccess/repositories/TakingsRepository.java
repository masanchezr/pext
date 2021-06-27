package com.sboot.golden.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.TakeEntity;

public interface TakingsRepository extends CrudRepository<TakeEntity, Long> {

	public TakeEntity findFirstByOrderByIdtakeDesc();

}
