package com.gu.services.employees;

import java.util.List;

import com.gu.dbaccess.entities.EmployeeEntity;

public interface EmployeeService {
	
	public List<EmployeeEntity> allEmployeesActives();

	public EmployeeEntity getEmployeeByUserName(String user);
}
