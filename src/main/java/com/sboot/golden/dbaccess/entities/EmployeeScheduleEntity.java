package com.sboot.golden.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employeeschedule")
public class EmployeeScheduleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idemployeeschedule")
	private Long idemployeeschedule;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE")
	private UserEntity employee;

	@ManyToOne
	@JoinColumn(name = "IDSCHEDULE")
	private ScheduleEntity schedule;

	/**
	 * @return the idemployeeschedule
	 */
	public Long getIdemployeeschedule() {
		return idemployeeschedule;
	}

	/**
	 * @param idemployeeschedule the idemployeeschedule to set
	 */
	public void setIdemployeeschedule(Long idemployeeschedule) {
		this.idemployeeschedule = idemployeeschedule;
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

	/**
	 * @return the schedule
	 */
	public ScheduleEntity getSchedule() {
		return schedule;
	}

	/**
	 * @param schedule the schedule to set
	 */
	public void setSchedule(ScheduleEntity schedule) {
		this.schedule = schedule;
	}

}
