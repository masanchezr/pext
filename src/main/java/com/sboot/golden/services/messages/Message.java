package com.sboot.golden.services.messages;

public class Message {
	private Long idmessage;
	private String smessage;
	private Boolean active;
	private String datefrom;
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
