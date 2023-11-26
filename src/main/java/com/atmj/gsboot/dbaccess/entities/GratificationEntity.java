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

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = Constants.GRATIFICATIONS)
public class GratificationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IDGRATIFICATION")
	private Long idgratification;

	@Column(name = "CREATIONDATE")
	@CreatedDate
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
	@JoinColumn(name = "idemployee")
	private UserEntity employee;

	@ManyToOne
	@JoinColumn(name = "idemployeepay")
	private UserEntity employeepay;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	@Column(name = "amount")
	private Integer amount;

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

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public UserEntity getEmployeepay() {
		return employeepay;
	}

	public void setEmployeepay(UserEntity employeepay) {
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
