package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "changemachine")
public class ChangeMachineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idchangemachine")
	private Long idchangemachine;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "amount")
	private BigDecimal amount;

	@OneToOne
	@JoinColumn(name = "idoperation", referencedColumnName = "idoperation")
	private OperationEntity operation;

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

	public OperationEntity getOperation() {
		return operation;
	}

	public void setOperation(OperationEntity operation) {
		this.operation = operation;
	}

}
