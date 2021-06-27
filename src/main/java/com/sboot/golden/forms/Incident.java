package com.sboot.golden.forms;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

public class Incident {

	private Long idincident;

	private String description;

	@CreatedDate
	private Date creationdate;

	private Boolean state;

	public Long getIdincident() {
		return idincident;
	}

	public void setIdincident(Long idincident) {
		this.idincident = idincident;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}
