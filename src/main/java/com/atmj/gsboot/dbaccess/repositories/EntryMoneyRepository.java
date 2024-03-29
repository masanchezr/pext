package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.EntryMoneyEntity;

public interface EntryMoneyRepository extends CrudRepository<EntryMoneyEntity, Long> {

	@Query("select o from EntryMoneyEntity o where DATE(o.creationdate)=:cdate")
	public List<EntryMoneyEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	public List<EntryMoneyEntity> findByCreationdateBetween(Date from, Date until);
}
