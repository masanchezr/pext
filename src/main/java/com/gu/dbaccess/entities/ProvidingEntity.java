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
@Table(name = Constants.STRINGPROVIDING)
public class ProvidingEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idproviding")
	private Long idproviding;

	@Column(name = Constants.CREATIONDATE)
	private Date creationdate;

	@Column(name = "amount")
	private BigDecimal amount;

	@Column(name = ConstantsJsp.TOTAL)
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
