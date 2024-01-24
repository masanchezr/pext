package com.atmj.gsboot.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

public class IncomeSportsBets {

	private Long idincomesportsbets;

	@CreatedDate
	private Date creationdate;

	private BigDecimal amount;

	private String description;

	public Long getIdincomesportsbets() {
		return idincomesportsbets;
	}

	public void setIdincomesportsbets(Long idincomesportsbets) {
		this.idincomesportsbets = idincomesportsbets;
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
