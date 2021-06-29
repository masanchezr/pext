package com.sboot.golden.dbaccess.entities;

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
@Table(name = "extrahours")
public class ExtraHoursEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idextrahours")
	private Long idextrahours;

	@Column(name = "systemtime")
	private Date systemtime;

	@Column(name = "departuretime")
	private Date departuretime;

	@Column(name = "description")
	private String description;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE")
	private UserEntity employee;

	/**
	 * @return the idextrahours
	 */
	public Long getIdextrahours() {
		return idextrahours;
	}

	/**
	 * @param idextrahours the idextrahours to set
	 */
	public void setIdextrahours(Long idextrahours) {
		this.idextrahours = idextrahours;
	}

	/**
	 * @return the systemtime
	 */
	public Date getSystemtime() {
		return systemtime;
	}

	/**
	 * @param systemtime the systemtime to set
	 */
	public void setSystemtime(Date systemtime) {
		this.systemtime = systemtime;
	}

	/**
	 * @return the departuretime
	 */
	public Date getDeparturetime() {
		return departuretime;
	}

	/**
	 * @param departuretime the departuretime to set
	 */
	public void setDeparturetime(Date departuretime) {
		this.departuretime = departuretime;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
