package com.gu.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.ChangeMachineTotalEntity;

public interface ChangeMachineTotalRepository extends CrudRepository<ChangeMachineTotalEntity, Long> {

	public ChangeMachineTotalEntity findFirstByOrderByIdchangemachinetotalDesc();

}
