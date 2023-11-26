package com.atmj.gsboot.dbaccess.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "schedule")
public class ScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idschedule")
	private Long idschedule;

	@Column(name = "dateschedule")
	private Date dateschedule;

	@ManyToOne
	@JoinColumn(name = "IDTIME", referencedColumnName = "IDTIME")
	private TimeEntity time;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE")
	private UserEntity employee;

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

	public TimeEntity getTime() {
		return time;
	}

	public void setTime(TimeEntity time) {
		this.time = time;
	}

	/**
	 * @return the employee
	 */
	public UserEntity getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}
}
