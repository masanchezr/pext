package com.sboot.golden.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.FunctionalityEntity;
import com.sboot.golden.dbaccess.entities.FunctionalityMachine;

public interface FunctionalitiesMachineRepository extends CrudRepository<FunctionalityMachine, Long> {

	public List<FunctionalityMachine> findByFunctionality(FunctionalityEntity functionality);

}
