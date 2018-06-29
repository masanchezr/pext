package com.gu.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.FunctionalityEntity;
import com.gu.dbaccess.entities.FunctionalityMachine;

public interface FunctionalitiesMachineRepository extends CrudRepository<FunctionalityMachine, Long> {

	public List<FunctionalityMachine> findByFunctionality(FunctionalityEntity functionality);

}
