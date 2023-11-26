package com.atmj.gsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.atmj.gsboot.util.constants.Constants;

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

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TimeEntity time = (TimeEntity) obj;
		return this.getIdtime().equals(time.getIdtime());
	}

	@Override
	public int hashCode() {
		return idtime.hashCode();
	}
}
