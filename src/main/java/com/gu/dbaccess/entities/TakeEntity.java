package com.gu.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "takings")
public class TakeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtake")
	private Long idtake;

	@Column(name = "takedate")
	private Date takedate;

	public Long getIdtake() {
		return idtake;
	}

	public void setIdtake(Long idtake) {
		this.idtake = idtake;
	}

	public Date getTakedate() {
		return takedate;
	}

	public void setTakedate(Date takedate) {
		this.takedate = takedate;
	}
}
