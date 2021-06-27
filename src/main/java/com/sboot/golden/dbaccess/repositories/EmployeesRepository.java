package com.sboot.golden.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.EmployeeEntity;

public interface EmployeesRepository extends CrudRepository<EmployeeEntity, Long> {

	public List<EmployeeEntity> findByEnabledTrue();

	public EmployeeEntity findByUsername(String user);

}
