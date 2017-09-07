package com.gu.services.register;

import java.util.Date;

import com.gu.dbaccess.entities.EmployeeEntity;

public class Register {
	private Date date;
	private EmployeeEntity employee;

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
