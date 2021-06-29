package com.sboot.golden.employee.forms;

import java.util.List;

import com.sboot.golden.dbaccess.entities.UserEntity;

public class CheckboxesEmp {

	private List<UserEntity> employees;

	public List<UserEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<UserEntity> employees) {
		this.employees = employees;
	}
}
