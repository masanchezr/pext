package com.atmj.gsboot.dbaccess.entities;

import com.atmj.gsboot.util.constants.Constants;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "awardschangemachine")
public class AwardsChangeMachineEntity {

	@Id
	@Column(name = "idawardchangemachine")
	private Long idawardchangemachine;

	@Column(name = Constants.NAME)
	private String name;

	public Long getIdawardchangemachine() {
		return idawardchangemachine;
	}

	public void setIdawardchangemachine(Long idawardchangemachine) {
		this.idawardchangemachine = idawardchangemachine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
