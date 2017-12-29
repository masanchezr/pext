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
@Table(name = "entrymoney")
public class EntryMoneyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identrymoney")
	private Long identrymoney;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = "creationdate")
	private Date creationdate;

	public Long getIdentrymoney() {
		return identrymoney;
	}

	public void setIdentrymoney(Long identrymoney) {
		this.identrymoney = identrymoney;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

}
