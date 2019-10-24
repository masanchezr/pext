package com.gu.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.gu.dbaccess.entities.CauseEntity;
import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.MachineEntity;

public class OpenMachine {

	private Long idopenmachine;

	private String description;

	@CreatedDate
	private Date creationdate;

	private BigDecimal amount;

	private EmployeeEntity employee;

	private MachineEntity machine;

	private CauseEntity cause;

	public Long getIdopenmachine() {
		return idopenmachine;
	}

	public void setIdopenmachine(Long idopenmachine) {
		this.idopenmachine = idopenmachine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public CauseEntity getCause() {
		return cause;
	}

	public void setCause(CauseEntity cause) {
		this.cause = cause;
	}

}
