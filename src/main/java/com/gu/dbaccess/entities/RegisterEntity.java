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

@Entity
@Table(name = "register")
public class RegisterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idregister")
	private Long idregister;

	@Column(name = "date")
	private Date date;

	@Column(name = "ipaddress")
	private String ipaddress;

	@Column(name = "outin")
	private Boolean outin;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE", referencedColumnName = "IDEMPLOYEE")
	private EmployeeEntity employee;

	public Long getIdregister() {
		return idregister;
	}

	public void setIdregister(Long idinemployee) {
		this.idregister = idinemployee;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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

	public Boolean getOutin() {
		return outin;
	}

	public void setOutin(Boolean outin) {
		this.outin = outin;
	}
}
