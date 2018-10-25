package com.gu.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class ScheduleEntity implements Comparable<ScheduleEntity> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idschedule")
	private Long idschedule;

	@Column(name = "dateschedule")
	private Date dateschedule;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE", referencedColumnName = "IDEMPLOYEE")
	private EmployeeEntity employee;

	@ManyToOne
	@JoinColumn(name = "IDTIME", referencedColumnName = "IDTIME")
	private TimeEntity time;

	public Long getIdschedule() {
		return idschedule;
	}

	public void setIdschedule(Long idschedule) {
		this.idschedule = idschedule;
	}

	public Date getDateschedule() {
		return dateschedule;
	}

	public void setDateschedule(Date dateschedule) {
		this.dateschedule = dateschedule;
	}

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public TimeEntity getTime() {
		return time;
	}

	public void setTime(TimeEntity time) {
		this.time = time;
	}

	public int compareTo(ScheduleEntity o) {
		return this.getTime().compareTo(o.getTime());
	}

	@Override
	public int hashCode() {
		return idschedule.intValue();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		} else {
			ScheduleEntity s = (ScheduleEntity) o;
			return s.getDateschedule().equals(this.dateschedule) && s.getTime().equals(this.time)
					&& s.getEmployee().equals(this.employee);
		}
	}
}
