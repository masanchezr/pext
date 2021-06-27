package com.sboot.golden.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.ChangeMachineTotalEntity;

public interface ChangeMachineTotalRepository extends CrudRepository<ChangeMachineTotalEntity, Long> {

	public ChangeMachineTotalEntity findFirstByOrderByIdchangemachinetotalDesc();

}
