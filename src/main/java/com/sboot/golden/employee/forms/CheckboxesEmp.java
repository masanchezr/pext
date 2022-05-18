package com.sboot.golden.employee.forms;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sboot.golden.dbaccess.entities.UserEntity;

public class CheckboxesEmp {

	@NotNull
	@NotEmpty(message = "{selectemployees}")
	private List<UserEntity> employees;

	public List<UserEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<UserEntity> employees) {
		this.employees = employees;
	}
}
