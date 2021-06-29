package com.sboot.golden.services.employees;

import java.util.List;

import com.sboot.golden.dbaccess.entities.UserEntity;

public interface EmployeeService {
	
	public List<UserEntity> allEmployeesActives();

	public UserEntity getEmployeeByUserName(String user);
}
