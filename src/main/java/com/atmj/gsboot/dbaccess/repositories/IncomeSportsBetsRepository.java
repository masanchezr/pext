package com.atmj.gsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.IncomeSportsBetsEntity;

public interface IncomeSportsBetsRepository extends CrudRepository<IncomeSportsBetsEntity, Long> {

	@Query("select o from IncomeSportsBetsEntity o where DATE(o.creationdate)=:cdate")
	List<IncomeSportsBetsEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	@Query("select sum(o.amount) from IncomeSportsBetsEntity o where o.creationdate>=:from and o.creationdate<=:until")
	BigDecimal searchSumByMonth(@Param("from") Date from, @Param("until") Date until);

	List<IncomeSportsBetsEntity> findByCreationdateBetween(Date date, Date date2);

}
