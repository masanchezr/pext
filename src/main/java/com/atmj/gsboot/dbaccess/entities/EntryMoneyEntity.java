package com.atmj.gsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = "entrymoney")
public class EntryMoneyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "identrymoney")
	private Long identrymoney;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = Constants.DESCRIPTION)
	private String description;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
