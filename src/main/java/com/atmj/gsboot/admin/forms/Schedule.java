package com.atmj.gsboot.admin.forms;

import com.atmj.gsboot.dbaccess.entities.TimeEntity;
import com.atmj.gsboot.dbaccess.entities.UserEntity;

public class Schedule {
	private String dateschedule;
	private UserEntity employee;
	private TimeEntity time;

	public String getDateschedule() {
		return dateschedule;
	}

	public void setDateschedule(String dateschedule) {
		this.dateschedule = dateschedule;
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
		result = prime * result + ((employee == null) ? 0 : employee.hashCode());
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
		if (employee == null) {
			if (other.employee != null) {
				return false;
			}
		} else if (!employee.equals(other.employee)) {
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

	public UserEntity getEmployee() {
		return employee;

	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;

	}
}
