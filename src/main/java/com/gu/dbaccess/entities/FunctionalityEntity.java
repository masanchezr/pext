package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "functionalities")
public class FunctionalityEntity {

	@Id
	@Column(name = "idfuncionality")
	private Long idfuncionality;

	@Column(name = Constants.DESCRIPTION)
	private String description;

	public Long getIdfuncionality() {
		return idfuncionality;
	}

	public void setIdfuncionality(Long idfuncionality) {
		this.idfuncionality = idfuncionality;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
