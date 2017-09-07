package com.gu.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.ProvidingEntity;

public interface ProvidingRepository extends CrudRepository<ProvidingEntity, Long> {
	public ProvidingEntity findFirstByOrderByIdprovidingDesc();

	public List<ProvidingEntity> findByCreationdateBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);
}
