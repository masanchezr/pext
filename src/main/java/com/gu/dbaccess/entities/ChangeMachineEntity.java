package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "changemachine")
public class ChangeMachineEntity {

	@Id
	@Column(name = "idchangemachine")
	private Long idchangemachine;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "amount")
	private BigDecimal amount;

	@OneToOne
	@JoinColumn(name = "idawardchangemachine", referencedColumnName = "idawardchangemachine")
	private AwardsChangeMachineEntity award;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
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

	public AwardsChangeMachineEntity getOperation() {
		return award;
	}

	public void setOperation(AwardsChangeMachineEntity operation) {
		this.award = operation;
	}

	public AwardsChangeMachineEntity getAward() {
		return award;
	}

	public void setAward(AwardsChangeMachineEntity award) {
		this.award = award;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

}
