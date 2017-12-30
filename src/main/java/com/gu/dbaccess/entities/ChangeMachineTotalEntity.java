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
@Table(name = "changemachinetotal")
public class ChangeMachineTotalEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idchangemachinetotal")
	private Long idchangemachinetotal;

	@Column(name = "creationdate")
	private Date creationdate;

	@Column(name = "total")
	private BigDecimal total;

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

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
}
