package com.sboot.golden.boss.forms;

import java.math.BigDecimal;

import com.sboot.golden.dbaccess.entities.PaymentEntity;

public class TPV {
	private Long idtpv;
	private String creationdate;
	private BigDecimal amount;
	private PaymentEntity pay;

	public Long getIdtpv() {
		return idtpv;
	}

	public void setIdtpv(Long idtpv) {
		this.idtpv = idtpv;
	}

	public String getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(String creationdate) {
		this.creationdate = creationdate;
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
