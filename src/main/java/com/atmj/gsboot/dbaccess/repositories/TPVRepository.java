package com.atmj.gsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.PaymentEntity;
import com.atmj.gsboot.dbaccess.entities.TPVEntity;

public interface TPVRepository extends CrudRepository<TPVEntity, Long> {

	@Query("select o from TPVEntity o where DATE(o.creationdate)=:cdate")
	public List<TPVEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	public List<TPVEntity> findByCreationdateBetween(Date from, Date until);

	@Query("select sum(o.amount) from TPVEntity o where DATE(o.creationdate)>=:from and DATE(o.creationdate)<=:until")
	public BigDecimal sumByCreationdate(@Temporal(TemporalType.DATE) @Param("from") Date from,
			@Temporal(TemporalType.DATE) @Param("until") Date until);

	@Query("select sum(o.amount) from TPVEntity o where o.creationdate>=:from and o.creationdate<=:until and o.pay=:payment")
	public BigDecimal sumByCreationdateAndPayment(@Param("payment") PaymentEntity pay, @Param("from") Date from,
			@Param("until") Date until);

}
