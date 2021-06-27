package com.sboot.golden.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

public class BarDrink {

	private Long idbardrink;

	@CreatedDate
	private Date creationdate;

	private BigDecimal amount;

	private String description;

	public Long getIdbardrink() {
		return idbardrink;
	}

	public void setIdbardrink(Long idincome) {
		this.idbardrink = idincome;
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
}