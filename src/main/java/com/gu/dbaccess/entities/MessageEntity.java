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

import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Entity
@Table(name = "messages")
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idmessage")
	private Long idmessage;

	@Column(name = ConstantsJsp.MESSAGE)
	private String message;

	@Column(name = Constants.ACTIVE)
	private Boolean active;

	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Temporal(TemporalType.TIME)
	@Column(name = ConstantsJsp.DATEFROM)
	private Date datefrom;

	@Temporal(TemporalType.TIME)
	@Column(name = ConstantsJsp.DATEUNTIL)
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
