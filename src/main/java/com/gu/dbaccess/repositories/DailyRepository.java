package com.gu.dbaccess.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.DailyEntity;

/**
 * The Interface DailyRepository.
 */
public interface DailyRepository extends CrudRepository<DailyEntity, Date> {
	public DailyEntity findFirstByOrderByDailydateDesc();
}
