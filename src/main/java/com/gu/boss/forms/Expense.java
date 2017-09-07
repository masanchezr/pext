package com.gu.boss.forms;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.AwardEntity;

public class Expense {

	private AwardEntity award;

	private BigDecimal amount;

	public AwardEntity getAward() {
		return award;
	}

	public void setAward(AwardEntity award) {
		this.award = award;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
