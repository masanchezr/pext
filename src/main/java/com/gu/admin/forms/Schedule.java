package com.gu.admin.forms;

import java.util.List;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.TimeEntity;

public class Schedule {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateschedule == null) ? 0 : dateschedule.hashCode());
		result = prime * result + ((employees == null) ? 0 : employees.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		if (dateschedule == null) {
			if (other.dateschedule != null) {
				return false;
			}
		} else if (!dateschedule.equals(other.dateschedule)) {
			return false;
		}
		if (employees == null) {
			if (other.employees != null) {
				return false;
			}
		} else if (!employees.equals(other.employees)) {
			return false;
		}
		if (time == null) {
			if (other.time != null) {
				return false;
			}
		} else if (!time.equals(other.time)) {
			return false;
		}
		return true;
	}
}
