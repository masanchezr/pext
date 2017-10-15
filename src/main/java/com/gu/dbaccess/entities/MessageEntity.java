package com.gu.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "messages")
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idmessage")
	private Long idmessage;

	@Column(name = "message")
	private String message;

	@Column(name = "active")
	private Boolean active;

	@Temporal(TemporalType.DATE)
	@Column(name = "creationdate")
	private Date creationdate;

	@Temporal(TemporalType.TIME)
	@Column(name = "datefrom")
	private Date datefrom;

	@Temporal(TemporalType.TIME)
	@Column(name = "dateuntil")
	private Date dateuntil;

	public Long getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(Long idmessage) {
		this.idmessage = idmessage;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public Date getDatefrom() {
		return datefrom;
	}

	public void setDatefrom(Date datefrom) {
		this.datefrom = datefrom;
	}

	public Date getDateuntil() {
		return dateuntil;
	}

	public void setDateuntil(Date dateuntil) {
		this.dateuntil = dateuntil;
	}
}
