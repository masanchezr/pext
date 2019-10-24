package com.gu.admin.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.gu.dbaccess.entities.AwardsChangeMachineEntity;
import com.gu.dbaccess.entities.MachineEntity;

public class ChangeMachine {

	private Long idchangemachine;

	@CreatedDate
	private Date creationdate;

	private BigDecimal amount;

	private AwardsChangeMachineEntity award;

	private MachineEntity machine;

	public Long getIdchangemachine() {
		return idchangemachine;
	}

	public void setIdchangemachine(Long idchangemachine) {
		this.idchangemachine = idchangemachine;
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

	public void setOperation(AwardsChangeMachineEntity operation) {
		this.award = operation;
	}

	public AwardsChangeMachineEntity getAward() {
		return award;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public void setAward(AwardsChangeMachineEntity award) {
		this.award = award;
	}

}
