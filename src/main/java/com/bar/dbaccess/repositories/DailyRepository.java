package com.bar.dbaccess.repositories;

import java.util.Date;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.bar.dbaccess.entities.DailyEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface DailyRepository.
 */
public interface DailyRepository extends CrudRepository<DailyEntity, Long> {

	/**
	 * Find by place and dailydate.
	 *
	 * @param dailydate            the dailydate
	 * @return the list
	 */
	public DailyEntity findByDailydate(@Temporal(TemporalType.DATE) Date dailydate);
}
