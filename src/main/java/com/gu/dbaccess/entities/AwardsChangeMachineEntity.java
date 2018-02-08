package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;

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
