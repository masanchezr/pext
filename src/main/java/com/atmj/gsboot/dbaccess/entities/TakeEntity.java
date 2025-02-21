package com.atmj.gsboot.dbaccess.entities;

import java.util.Date;

import com.atmj.gsboot.util.constants.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = Constants.TAKINGS)
public class TakeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
