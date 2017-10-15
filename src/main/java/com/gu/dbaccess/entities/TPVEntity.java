package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tpvs")
public class TPVEntity {

	@Id
	@Column(name = "idtpv")
	private Long idtpv;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "amount")
	private BigDecimal amount;

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
}
