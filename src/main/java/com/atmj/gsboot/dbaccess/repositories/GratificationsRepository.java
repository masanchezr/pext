package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.GratificationEntity;
import com.atmj.gsboot.util.constants.Constants;

public interface GratificationsRepository extends CrudRepository<GratificationEntity, Long> {

	@Query("select o from GratificationEntity o where DATE(o.paydate)=:cdate order by o.paydate")
	public List<GratificationEntity> searchByPaydate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	public List<GratificationEntity> findByCreationdateBetweenAndPaydateIsNull(Date lastdate, Date creationdate);

	public List<GratificationEntity> findByPaydateBetween(Date date, Date date2);

	@Query("select o from GratificationEntity o where o.idgratification=:id and o.paydate is null and o.usefromdate<=:date and o.expirationdate>=:date")
	public GratificationEntity findByIdgratificationAndPaydateIsNull(@Param("id") Long idgratification,
			@Param(Constants.DATE) Date date);
}
