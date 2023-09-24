package com.atmj.gsboot.admin.forms;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.dbaccess.entities.AwardsChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.MachineEntity;
import com.atmj.gsboot.util.constants.ConstantsViews;

public class ChangeMachine {

	private Long idchangemachine;

	@CreatedDate
	private Date creationdate;

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@DecimalMin(value = "0.1")
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
