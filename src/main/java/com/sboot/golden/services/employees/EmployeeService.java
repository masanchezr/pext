package com.sboot.golden.services.employees;

import java.util.List;

import com.sboot.golden.dbaccess.entities.EmployeeEntity;

public interface EmployeeService {
	
	public List<EmployeeEntity> allEmployeesActives();

	public EmployeeEntity getEmployeeByUserName(String user);
}
