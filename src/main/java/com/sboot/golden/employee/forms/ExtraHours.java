package com.sboot.golden.employee.forms;

import com.sboot.golden.dbaccess.entities.EmployeeEntity;

public class ExtraHours {

	private String departuretime;

	private String description;

	private EmployeeEntity employee;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

}
