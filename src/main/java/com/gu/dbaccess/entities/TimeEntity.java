package com.gu.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "timetable")
public class TimeEntity implements Comparable<TimeEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idtime")
	private Long idtime;

	@Column(name = Constants.NAME)
	private String name;

	@Column(name = "entry")
	private Date entry;

	@Column(name = "departure")
	private Date departure;

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

	public Date getEntry() {
		return entry;
	}

	public void setEntry(Date entry) {
		this.entry = entry;
	}

	public Date getDeparture() {
		return departure;
	}

	public void setDeparture(Date departure) {
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

	public int compareTo(TimeEntity o) {
		return this.getOrder().compareTo(o.getOrder());
	}

	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (this.getClass() != o.getClass()) {
				return false;
			} else {
				return this.getIdtime().equals(((TimeEntity) o).getIdtime());
			}
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return idtime.hashCode();
	}
}
