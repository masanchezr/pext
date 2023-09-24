package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.DailyEntity;

/**
 * The Interface DailyRepository.
 */
public interface DailyRepository extends CrudRepository<DailyEntity, Date> {
}
