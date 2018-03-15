package com.gu.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gu.dbaccess.entities.RegisterEntity;

public interface RegisterRepository extends CrudRepository<RegisterEntity, Long> {

	@Query("select o from RegisterEntity o where DATE(o.date)=:cdate")
	public List<RegisterEntity> selectByDate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	public List<RegisterEntity> findByDateBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);
}
