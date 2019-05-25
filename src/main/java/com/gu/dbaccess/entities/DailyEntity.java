package com.gu.dbaccess.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
