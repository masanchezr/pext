package com.gu.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@JoinColumn(name = "IDEMPLOYEE", referencedColumnName = "IDEMPLOYEE")
	private EmployeeEntity employee;

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

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
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
