package com.gu.services.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.repositories.EmployeesRepository;

public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeesRepository employeesRepository;

	public List<EmployeeEntity> allEmployeesActives() {
		return employeesRepository.findByEnabledTrue();
	}

	public EmployeeEntity getEmployeeByUserName(String user) {
		return employeesRepository.findByUsername(user);
	}
}
