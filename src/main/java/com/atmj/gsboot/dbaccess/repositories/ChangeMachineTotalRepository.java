package com.atmj.gsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.ChangeMachineTotalEntity;

public interface ChangeMachineTotalRepository extends CrudRepository<ChangeMachineTotalEntity, Long> {

	public ChangeMachineTotalEntity findFirstByOrderByIdchangemachinetotalDesc();

}
