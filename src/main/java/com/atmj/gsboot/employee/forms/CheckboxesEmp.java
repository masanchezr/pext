package com.atmj.gsboot.employee.forms;

import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.atmj.gsboot.dbaccess.entities.UserEntity;

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
