package com.sboot.golden.dbaccess.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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

	@JoinColumn(name = "IDSCHEDULE")
	@OneToMany(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<EmployeeScheduleEntity> employees;

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
	 * @return the employees
	 */
	public List<EmployeeScheduleEntity> getEmployees() {
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(List<EmployeeScheduleEntity> employees) {
		this.employees = employees;
	}
}
