package com.sboot.golden.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sboot.golden.util.constants.Constants;

@Entity
@Table(name = Constants.MACHINES)
public class MachineEntity {

	@Id
	@Column(name = "idmachine")
	private Long idmachine;

	@Column(name = Constants.NAME)
	private String name;

	@Column(name = "onoff")
	private Boolean onoff;

	@Column(name = "order")
	private Long ordermachine;

	@Column(name = "nameticket")
	private String nameticket;

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

	public Long getOrdermachine() {
		return ordermachine;
	}

	public void setOrdermachine(Long order) {
		this.ordermachine = order;
	}

	public String getNameticket() {
		return nameticket;
	}

	public void setNameticket(String nameticket) {
		this.nameticket = nameticket;
	}

}
