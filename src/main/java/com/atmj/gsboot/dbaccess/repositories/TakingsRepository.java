package com.atmj.gsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.TakeEntity;

public interface TakingsRepository extends CrudRepository<TakeEntity, Long> {

	public TakeEntity findFirstByOrderByIdtakeDesc();

}
