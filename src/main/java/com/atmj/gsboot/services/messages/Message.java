package com.atmj.gsboot.services.messages;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.atmj.gsboot.util.constants.ConstantsViews;

public class Message {
	private Long idmessage;

	@NotNull(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDESCRIPTION)
	private String smessage;
	private Boolean active;

	@NotNull(message = ConstantsViews.ERRORSELECTDATE)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDATE)
	private String datefrom;

	@NotNull(message = ConstantsViews.ERRORSELECTDATE)
	@NotEmpty(message = ConstantsViews.ERRORSELECTDATE)
	private String dateuntil;

	public Long getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(Long idmessage) {
		this.idmessage = idmessage;
	}

	public String getSmessage() {
		return smessage;
	}

	public void setSmessage(String message) {
		this.smessage = message;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(String datefrom) {
		this.datefrom = datefrom;
	}

	public String getDateuntil() {
		return dateuntil;
	}

	public void setDateuntil(String dateuntil) {
		this.dateuntil = dateuntil;
	}

}
