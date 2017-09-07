package com.gu.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "gratifications")
public class GratificationEntity {

	@Id
	@Column(name = "IDGRATIFICATION")
	private Long idgratification;

	@Column(name = "CREATIONDATE")
	private Date creationdate;

	@Column(name = "PAYDATE")
	private Date paydate;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	public Long getIdgratification() {
		return idgratification;
	}

	public void setIdgratification(Long idgratification) {
		this.idgratification = idgratification;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public Date getPaydate() {
		return paydate;
	}

	public void setPaydate(Date paydate) {
		this.paydate = paydate;
	}
}
