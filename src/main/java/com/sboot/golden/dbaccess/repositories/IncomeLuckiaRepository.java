package com.sboot.golden.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sboot.golden.dbaccess.entities.IncomeLuckiaEntity;

public interface IncomeLuckiaRepository extends CrudRepository<IncomeLuckiaEntity, Long> {

	@Query("select o from IncomeLuckiaEntity o where DATE(o.creationdate)=:cdate")
	List<IncomeLuckiaEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	@Query("select sum(o.amount) from IncomeLuckiaEntity o where o.creationdate>=:from and o.creationdate<=:until")
	BigDecimal searchSumByMonth(@Param("from") Date from, @Param("until") Date until);

	List<IncomeLuckiaEntity> findByCreationdateBetween(Date date, Date date2);

}
