package com.atmj.gsboot.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.dbaccess.entities.MachineEntity;

public class IncomeMachine {

	private Long idincomemachine;

	@CreatedDate
	private Date creationdate;

	private BigDecimal amount;

	private String description;

	private MachineEntity machine;

	public Long getIdincomemachine() {
		return idincomemachine;
	}

	public void setIdincomemachine(Long idincomemachine) {
		this.idincomemachine = idincomemachine;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

}
