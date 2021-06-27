package com.sboot.golden.services.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.EmployeeEntity;
import com.sboot.golden.dbaccess.repositories.EmployeesRepository;

@Service
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
