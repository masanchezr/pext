package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "incomeluckia")
public class IncomeLuckiaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idincomeluckia")
	private Long idincomeluckia;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = Constants.DESCRIPTION)
	private String description;

	public Long getIdincomeluckia() {
		return idincomeluckia;
	}

	public void setIdincomeluckia(Long idincomeluckia) {
		this.idincomeluckia = idincomeluckia;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
