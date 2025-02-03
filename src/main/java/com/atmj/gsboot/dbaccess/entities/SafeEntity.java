package com.atmj.gsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "safe")
public class SafeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsafe")
	private Long idsafe;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Column(name = ConstantsViews.TOTAL)
	private BigDecimal total;

	@Column(name = Constants.DESCRIPTION)
	private String description;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdsafe() {
		return idsafe;
	}

	public void setIdsafe(Long idsafe) {
		this.idsafe = idsafe;
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}
}
