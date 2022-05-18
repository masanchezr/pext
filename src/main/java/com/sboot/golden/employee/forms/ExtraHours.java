package com.sboot.golden.employee.forms;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.sboot.golden.services.users.User;
import com.sboot.golden.util.constants.ConstantsViews;

public class ExtraHours {

	@NotNull(message = "{selectdeparturetime}")
	@NotEmpty(message = "{selectdeparturetime}")
	private String departuretime;

	@NotNull(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	private String description;

	private User employee;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getEmployee() {
		return employee;
	}

	public void setEmployee(User employee) {
		this.employee = employee;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

}
