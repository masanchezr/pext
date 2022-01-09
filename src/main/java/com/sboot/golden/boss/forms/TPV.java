package com.sboot.golden.boss.forms;

import java.math.BigDecimal;

import com.sboot.golden.dbaccess.entities.PaymentEntity;

public class TPV {
	private Long idtpv;
	private String sdate;
	private BigDecimal amount;
	private PaymentEntity pay;

	public Long getIdtpv() {
		return idtpv;
	}

	public void setIdtpv(Long idtpv) {
		this.idtpv = idtpv;
	}

	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}
}
