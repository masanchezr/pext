package com.bar.admin.forms;

import java.math.BigDecimal;

public class EntryMoneyForm {
	private BigDecimal amount;
	private String origin;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
