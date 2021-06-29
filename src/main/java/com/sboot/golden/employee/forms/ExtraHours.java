package com.sboot.golden.employee.forms;

import com.sboot.golden.dbaccess.entities.UserEntity;

public class ExtraHours {

	private String departuretime;

	private String description;

	private UserEntity employee;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

}
