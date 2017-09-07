package com.gu.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gu.dbaccess.entities.IncomeMachineEntity;

public interface IncomeMachinesRepository extends CrudRepository<IncomeMachineEntity, Long> {

	@Query("select o from IncomeMachineEntity o where DATE(o.creationdate)=:cdate")
	List<IncomeMachineEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	@Query("select sum(o.amount) from IncomeMachineEntity o where o.creationdate>=:from and o.creationdate<=:until")
	BigDecimal searchSumByMonth(@Param("from") Date from, @Param("until") Date until);

	List<IncomeMachineEntity> findByCreationdateBetween(Date date, Date date2);

}
