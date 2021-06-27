package com.sboot.golden.dbaccess.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.DailyEntity;

/**
 * The Interface DailyRepository.
 */
public interface DailyRepository extends CrudRepository<DailyEntity, Date> {
}
