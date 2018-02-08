package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Entity
@Table(name = "safe")
public class SafeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idsafe")
	private Long idsafe;

	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Column(name = ConstantsJsp.TOTAL)
	private BigDecimal total;

	@Column(name = Constants.DESCRIPTION)
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getIdsafe() {
		return idsafe;
	}

	public void setIdsafe(Long idsafe) {
		this.idsafe = idsafe;
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
