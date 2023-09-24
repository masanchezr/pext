package com.atmj.gsboot.boss.forms;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.atmj.gsboot.dbaccess.entities.PaymentEntity;
import com.atmj.gsboot.util.constants.ConstantsViews;

public class TPV {

	@NotNull(message = ConstantsViews.ERRORSELECTID)
	private Long idtpv;

	@NotNull
	@NotEmpty(message = ConstantsViews.ERRORSELECTDATE)
	private String sdate;

	@NotNull(message = ConstantsViews.ERRORSELECTAMOUNT)
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
