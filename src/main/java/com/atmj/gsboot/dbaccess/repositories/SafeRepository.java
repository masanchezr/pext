package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.SafeEntity;

public interface SafeRepository extends CrudRepository<SafeEntity, Long> {

	public SafeEntity findFirstByOrderByIdsafeDesc();

	public List<SafeEntity> findByCreationdateBetween(Date from, Date until);

}
