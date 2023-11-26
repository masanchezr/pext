package com.atmj.gsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = "awards")
public class AwardEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
