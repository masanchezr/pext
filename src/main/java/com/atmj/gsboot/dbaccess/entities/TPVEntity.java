package com.atmj.gsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = "tpvs")
public class TPVEntity {

	@Id
	@Column(name = Constants.IDTPV)
	private Long idtpv;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	public Long getIdtpv() {
		return idtpv;
	}

	public void setIdtpv(Long idtpv) {
		this.idtpv = idtpv;
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

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}
}
