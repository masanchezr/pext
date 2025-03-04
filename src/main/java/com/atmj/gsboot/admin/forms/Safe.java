package com.atmj.gsboot.admin.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.dbaccess.entities.MachineEntity;
import com.atmj.gsboot.util.constants.ConstantsViews;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class Safe {
	private Long idsafe;

	@CreatedDate
	private Date creationdate;

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@DecimalMin(value = "0.1")
	private BigDecimal amount;

	private BigDecimal total;

	private String description;

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
