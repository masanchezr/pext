package com.atmj.gsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.UserEntity;
import com.atmj.gsboot.dbaccess.entities.ReturnMoneyEmployeeEntity;

public interface ReturnMoneyEmployeesRepository extends CrudRepository<ReturnMoneyEmployeeEntity, Long> {

	@Query("select o from ReturnMoneyEmployeeEntity o where DATE(o.creationdate)=:cdate")
	List<ReturnMoneyEmployeeEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	@Query("select sum(o.amount) from ReturnMoneyEmployeeEntity o where o.creationdate>=:from and o.creationdate<=:until and o.returndate is null and o.employee=:employee")
	BigDecimal searchSumAdvanceByMonth(@Param("from") Date from, @Param("until") Date until,
			@Param("employee") UserEntity employee);

	List<ReturnMoneyEmployeeEntity> findByCreationdateBetween(Date date, Date date2);

	@Query("select o from ReturnMoneyEmployeeEntity o where DATE(o.returndate)=:cdate")
	List<ReturnMoneyEmployeeEntity> findByReturndate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	List<ReturnMoneyEmployeeEntity> findByEmployeeAndReturndateIsNull(UserEntity employee);

	List<ReturnMoneyEmployeeEntity> findByReturndateBetween(Date date, Date date2);

	@Query("select sum(o.amount) from ReturnMoneyEmployeeEntity o where o.returndate>=:from and o.returndate<=:until")
	BigDecimal searchSumReturnByMonth(@Param("from") Date from, @Param("until") Date until);

}
