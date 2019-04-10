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

@Entity
@Table(name = "changemachinetotal")
public class ChangeMachineTotalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idchangemachinetotal")
	private Long idchangemachinetotal;

	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Column(name = "VISIBLE")
	private BigDecimal visible;

	@Column(name = "DEPOSIT")
	private BigDecimal deposit;

	public Long getIdchangemachinetotal() {
		return idchangemachinetotal;
	}

	public void setIdchangemachinetotal(Long idchangemachinetotal) {
		this.idchangemachinetotal = idchangemachinetotal;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public BigDecimal getVisible() {
		return visible;
	}

	public void setVisible(BigDecimal total) {
		this.visible = total;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}
}
