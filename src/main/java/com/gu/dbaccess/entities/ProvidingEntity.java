package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "providing")
public class ProvidingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idproviding")
	private Long idproviding;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "total")
	private BigDecimal total;

	public Long getIdproviding() {
		return idproviding;
	}

	public void setIdproviding(Long idproviding) {
		this.idproviding = idproviding;
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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

}
