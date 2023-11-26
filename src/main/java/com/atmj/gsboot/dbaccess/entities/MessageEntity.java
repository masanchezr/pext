package com.atmj.gsboot.dbaccess.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = "messages")
public class MessageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmessage")
	private Long idmessage;

	@Column(name = Constants.MESSAGE)
	private String message;

	@Column(name = Constants.ACTIVE)
	private Boolean active;

	@Temporal(TemporalType.DATE)
	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Temporal(TemporalType.TIME)
	@Column(name = Constants.DATEFROM)
	private Date datefrom;

	@Temporal(TemporalType.TIME)
	@Column(name = Constants.DATEUNTIL)
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
