package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import jakarta.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.SafeEntity;

public interface SafeRepository extends CrudRepository<SafeEntity, Long> {

	public SafeEntity findFirstByOrderByIdsafeDesc();

	public List<SafeEntity> findByCreationdateBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

}
