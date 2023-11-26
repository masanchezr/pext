package com.atmj.gsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "causes")
public class CauseEntity {

	@Id
	@Column(name = "idcause")
	private Long idcause;

	@Column(name = "cause")
	private String cause;

	public Long getIdcause() {
		return idcause;
	}

	public void setIdcause(Long idcause) {
		this.idcause = idcause;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

}
