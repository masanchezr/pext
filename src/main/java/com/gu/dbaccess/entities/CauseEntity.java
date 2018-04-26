package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
