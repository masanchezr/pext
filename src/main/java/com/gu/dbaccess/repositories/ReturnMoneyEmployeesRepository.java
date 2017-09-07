package com.gu.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;

public interface ReturnMoneyEmployeesRepository extends CrudRepository<ReturnMoneyEmployeeEntity, Long> {

	@Query("select o from ReturnMoneyEmployeeEntity o where DATE(o.creationdate)=:cdate")
	List<ReturnMoneyEmployeeEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	@Query("select sum(o.amount) from ReturnMoneyEmployeeEntity o where o.creationdate>=:from and o.creationdate<=:until")
	BigDecimal searchSumByMonth(@Param("from") Date from, @Param("until") Date until);

	List<ReturnMoneyEmployeeEntity> findByCreationdateBetween(Date date, Date date2);

}
