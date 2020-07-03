package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "timetable")
public class TimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idtime")
	private Long idtime;

	@Column(name = Constants.NAME)
	private String name;

	@Column(name = "entry")
	private String entry;

	@Column(name = "departure")
	private String departure;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "order")
	private Integer order;

	public Long getIdtime() {
		return idtime;
	}

	public void setIdtime(Long idtime) {
		this.idtime = idtime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	@Override
	public int hashCode() {
		return idtime.hashCode();
	}
}
