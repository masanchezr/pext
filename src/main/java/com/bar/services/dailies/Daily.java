package com.bar.services.dailies;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.bar.dbaccess.entities.EntryMoneyEntity;
import com.bar.dbaccess.entities.IncomeEntity;

/**
 * The Class Daily.
 */
public class Daily {

	/** The finalamount. */
	private BigDecimal finalamount;

	private int numoperations;

	private List<EntryMoneyEntity> entriesMoney;

	private List<IncomeEntity> income;

	private Date date;

	public BigDecimal getFinalamount() {
		return finalamount;
	}

	public void setFinalamount(BigDecimal finalamount) {
		this.finalamount = finalamount;
	}

	public int getNumoperations() {
		return numoperations;
	}

	public void setNumoperations(int numoperations) {
		this.numoperations = numoperations;
	}

	public List<EntryMoneyEntity> getEntriesMoney() {
		return entriesMoney;
	}

	public void setEntriesMoney(List<EntryMoneyEntity> entriesMoney) {
		this.entriesMoney = entriesMoney;
	}

	public List<IncomeEntity> getIncome() {
		return income;
	}

	public void setIncome(List<IncomeEntity> income) {
		this.income = income;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
