package com.sboot.golden.forms;

import java.math.BigDecimal;
import java.util.Date;

import com.sboot.golden.dbaccess.entities.AwardEntity;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.entities.MachineEntity;
import com.sboot.golden.dbaccess.entities.PaymentEntity;

public class Operation {

	private Long idoperation;

	private Date creationdate;

	private BigDecimal amount;

	private MachineEntity machine;

	private AwardEntity award;

	private PaymentEntity pay;

	private UserEntity employee;

	private String description;

	public Long getIdoperation() {
		return idoperation;
	}

	public void setIdoperation(Long idoperation) {
		this.idoperation = idoperation;
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

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public AwardEntity getAward() {
		return award;
	}

	public void setAward(AwardEntity award) {
		this.award = award;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
