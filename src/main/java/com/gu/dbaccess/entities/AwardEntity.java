package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "awards")
public class AwardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idaward")
	private Integer idaward;

	@Column(name = Constants.NAME)
	private String name;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "color")
	private String color;

	@Column(name = "order")
	private Integer order;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public void setIdaward(Integer idaward) {
		this.idaward = idaward;
	}

	public Integer getIdaward() {
		return idaward;
	}

}
