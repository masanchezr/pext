package com.sboot.golden.admin.forms;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.sboot.golden.util.constants.ConstantsViews;

public class EntryMoneyForm {

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
	@DecimalMin(value = "0.1")
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
