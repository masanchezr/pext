package com.bar.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

// TODO: Auto-generated Javadoc
/**
 * The Class EntryMoneyEntity.
 */
@Entity
@Table(name = "entrymoney")
public class EntryMoneyEntity {

	/** The identrymoney. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "identrymoney")
	private Long identrymoney;

	/** The amount. */
	@Column(name = "amount")
	private BigDecimal amount;

	/** The creationdate. */
	@Temporal(TemporalType.DATE)
	@Column(name = "creationdate")
	private Date creationdate;

	/**
	 * Gets the identrymoney.
	 *
	 * @return the identrymoney
	 */
	public Long getIdentrymoney() {
		return identrymoney;
	}

	/**
	 * Sets the identrymoney.
	 *
	 * @param identrymoney the new identrymoney
	 */
	public void setIdentrymoney(Long identrymoney) {
		this.identrymoney = identrymoney;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

}
