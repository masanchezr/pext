package com.sboot.golden.employee.forms;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.sboot.golden.dbaccess.entities.EmployeeEntity;
import com.sboot.golden.dbaccess.entities.MachineEntity;

public class Gratification {

	private Long idgratification;

	@CreatedDate
	private Date creationdate;

	private Date paydate;

	private Date expirationdate;

	private Date usefromdate;

	private String client;

	private EmployeeEntity employee;

	private EmployeeEntity employeepay;

	private MachineEntity machine;

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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
