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
@Table(name = "gratifications")
public class GratificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRATIFICATION")
	private Long idgratification;

	@Column(name = "CREATIONDATE")
	private Date creationdate;

	@Column(name = "PAYDATE")
	private Date paydate;

	@Column(name = "EXPIRATIONDATE")
	private Date expirationdate;

	@Column(name = "USEFROMDATE")
	private Date usefromdate;

	@Column(name = "CLIENT")
	private String client;

	@ManyToOne
	@JoinColumn(name = "idemployee", referencedColumnName = "idemployee")
	private EmployeeEntity employee;

	@ManyToOne
	@JoinColumn(name = "idemployeepay", referencedColumnName = "idemployee")
	private EmployeeEntity employeepay;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	public Long getIdgratification() {
		return idgratification;
	}

	public void setIdgratification(Long idgratification) {
		this.idgratification = idgratification;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public EmployeeEntity getEmployeepay() {
		return employeepay;
	}

	public void setEmployeepay(EmployeeEntity employeepay) {
		this.employeepay = employeepay;
	}

	public Date getExpirationdate() {
		return expirationdate;
	}

	public void setExpirationdate(Date expirationdate) {
		this.expirationdate = expirationdate;
	}

	public Date getUsefromdate() {
		return usefromdate;
	}

	public void setUsefromdate(Date usefromdate) {
		this.usefromdate = usefromdate;
	}
}
