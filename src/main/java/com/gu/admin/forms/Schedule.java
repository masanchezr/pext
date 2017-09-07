package com.gu.admin.forms;

import java.util.List;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.TimeEntity;

public class Schedule implements Comparable<Schedule> {
	private String dateschedule;
	private List<EmployeeEntity> employees;
	private TimeEntity time;

	public String getDateschedule() {
		return dateschedule;
	}

	public void setDateschedule(String dateschedule) {
		this.dateschedule = dateschedule;
	}

	public List<EmployeeEntity> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeEntity> employees) {
		this.employees = employees;
	}

	public TimeEntity getTime() {
		return time;
	}

	public void setTime(TimeEntity time) {
		this.time = time;
	}

	public int compareTo(Schedule o) {
		return this.getTime().compareTo(o.getTime());
	}
}
