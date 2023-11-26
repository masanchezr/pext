package com.atmj.gsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.atmj.gsboot.util.constants.Constants;

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
