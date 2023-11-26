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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "ficticionalregister")
public class FicticionalRegisterEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "creationdate")
	private Date creationdate;

	@Temporal(TemporalType.TIME)
	@Column(name = "timein")
	private Date timein;

	@Temporal(TemporalType.TIME)
	@Column(name = "timeout")
	private Date timeout;

	@ManyToOne
	@JoinColumn(name = "IDEMPLOYEE")
	private UserEntity employee;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * @param creationdate the creationdate to set
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * @return the timein
	 */
	public Date getTimein() {
		return timein;
	}

	/**
	 * @param timein the timein to set
	 */
	public void setTimein(Date timein) {
		this.timein = timein;
	}

	/**
	 * @return the timeout
	 */
	public Date getTimeout() {
		return timeout;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(Date timeout) {
		this.timeout = timeout;
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
