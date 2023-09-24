package com.atmj.gsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.AwardEntity;

public interface AwardsRepository extends CrudRepository<AwardEntity, Long> {

	public List<AwardEntity> findByActive(Boolean b);

	public List<AwardEntity> findByActiveOrderByOrder(boolean b);

}
