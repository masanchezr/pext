package com.atmj.gsboot.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.BarDrinkEntity;

public interface BarDrinksRepository extends CrudRepository<BarDrinkEntity, Long> {

	@Query("select o from BarDrinkEntity o where DATE(o.creationdate)=:cdate")
	public List<BarDrinkEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	@Query("select sum(o.amount) from BarDrinkEntity o where o.creationdate>=:from and o.creationdate<=:until")
	public BigDecimal searchSumByMonth(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until);

	public List<BarDrinkEntity> findByCreationdateBetween(Date from, Date until);
}
