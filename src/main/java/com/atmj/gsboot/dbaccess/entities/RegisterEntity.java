package com.atmj.gsboot.dbaccess.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "register")
public class RegisterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idregister")
	private Long idregister;

	@Temporal(TemporalType.DATE)
	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "ipaddress")
	private String ipaddress;

	@Temporal(TemporalType.TIME)
	@Column(name = "timein")
	private Date timein;

	@Temporal(TemporalType.TIME)
	@Column(name = "timeout")
	private Date timeout;

	@Column(name = "active")
	private Boolean active;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE")
	private UserEntity employee;

	public Long getIdregister() {
		return idregister;
	}

	public void setIdregister(Long idinemployee) {
		this.idregister = idinemployee;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date date) {
		this.creationdate = date;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public String getIpaddress() {
		return ipaddress;
	}

	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	public Date getTimeout() {
		return timeout;
	}

	public void setTimeout(Date timeout) {
		this.timeout = timeout;
	}

	public Date getTimein() {
		return timein;
	}

	public void setTimein(Date timein) {
		this.timein = timein;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
