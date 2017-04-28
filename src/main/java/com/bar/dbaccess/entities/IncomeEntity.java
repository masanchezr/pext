package com.bar.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class IncomeEntity.
 */
@Entity
@Table(name = "income")
public class IncomeEntity {

	/** The idincome. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idincome")
	private Long idincome;

	/** The creationdate. */
	@Column(name = "creationdate")
	private Date creationdate;

	/** The amount. */
	@Column(name = "amount")
	private BigDecimal amount;

	/** The description. */
	@Column(name = "description")
	private String description;

	/**
	 * Gets the idincome.
	 *
	 * @return the idincome
	 */
	public Long getIdincome() {
		return idincome;
	}

	/**
	 * Sets the idincome.
	 *
	 * @param idincome the new idincome
	 */
	public void setIdincome(Long idincome) {
		this.idincome = idincome;
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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
