package com.atmj.gsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.FunctionalityEntity;
import com.atmj.gsboot.dbaccess.entities.FunctionalityMachine;

public interface FunctionalitiesMachineRepository extends CrudRepository<FunctionalityMachine, Long> {

	public List<FunctionalityMachine> findByFunctionality(FunctionalityEntity functionality);

}
