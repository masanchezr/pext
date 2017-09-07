package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "machines")
public class MachineEntity {

	@Id
	@Column(name = "idmachine")
	private Long idmachine;

	@Column(name = "name")
	private String name;

	@Column(name = "onoff")
	private Boolean onoff;

	@Column(name = "order")
	private Long order;

	public Long getIdmachine() {
		return idmachine;
	}

	public void setIdmachine(Long idmachine) {
		this.idmachine = idmachine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getOnoff() {
		return onoff;
	}

	public void setOnoff(Boolean onoff) {
		this.onoff = onoff;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}

}
