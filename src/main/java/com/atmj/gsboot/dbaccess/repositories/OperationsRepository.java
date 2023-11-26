package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.OperationEntity;
import com.atmj.gsboot.dbaccess.entities.PaymentEntity;

public interface OperationsRepository extends CrudRepository<OperationEntity, Long> {

	@Query("select o from OperationEntity o where DATE(o.creationdate)=:cdate")
	public List<OperationEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	@Query("select sum(o.amount), o.award from OperationEntity o where DATE(o.creationdate)>=:from and DATE(o.creationdate)<=:until group by o.award")
	public List<Object[]> searchSumByMonth(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until);

	@Query("select o from OperationEntity o where DATE(o.creationdate)=:cdate and o.pay=:pay")
	public List<OperationEntity> findByPayAndCreationdate(@Param("pay") PaymentEntity pay,
			@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	public List<OperationEntity> findByCreationdateBetween(Date from, Date until);

}
