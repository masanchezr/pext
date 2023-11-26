package com.atmj.gsboot.dbaccess.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * The Class DailyEntity.
 */
@Entity
@Table(name = "dailies")
public class DailyEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@Temporal(TemporalType.DATE)
	@Column(name = "DAILYDATE")
	private Date dailydate;

	/** The finalamount. */
	@Column(name = "FINALAMOUNT")
	private BigDecimal finalamount;

	/**
	 * Gets the finalamount.
	 *
	 * @return the finalamount
	 */
	public BigDecimal getFinalamount() {
		return finalamount;
	}

	/**
	 * Sets the finalamount.
	 *
	 * @param finalamount the new finalamount
	 */
	public void setFinalamount(BigDecimal finalamount) {
		this.finalamount = finalamount;
	}

	/**
	 * Gets the dailydate.
	 *
	 * @return the dailydate
	 */
	public Date getDailydate() {
		return dailydate;
	}

	/**
	 * Sets the dailydate.
	 *
	 * @param dailydate the new dailydate
	 */
	public void setDailydate(Date dailydate) {
		this.dailydate = dailydate;
	}
}
