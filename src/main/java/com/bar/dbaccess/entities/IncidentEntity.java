package com.bar.dbaccess.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// TODO: Auto-generated Javadoc
/**
 * The Class IncidentEntity.
 */
@Entity
@Table(name = "incidents")
public class IncidentEntity {

	/** The idincident. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "IDINCIDENT")
	private Long idincident;

	/** The description. */
	@Column(name = "DESCRIPTION")
	private String description;

	/** The creationdate. */
	@Column(name = "CREATIONDATE")
	private Date creationdate;

	/** The state. */
	@Column(name = "STATE")
	private Boolean state;

	/** The username. */
	@ManyToOne
	@JoinColumn(name = "USERNAME", referencedColumnName = "USERNAME")
	private UserEntity username;

	/**
	 * Gets the idincident.
	 *
	 * @return the idincident
	 */
	public Long getIdincident() {
		return idincident;
	}

	/**
	 * Sets the idincident.
	 *
	 * @param idincident the new idincident
	 */
	public void setIdincident(Long idincident) {
		this.idincident = idincident;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the creationdate.
	 *
	 * @return the creationdate
	 */
	public Date getCreationdate() {
		return creationdate;
	}

	/**
	 * Sets the creationdate.
	 *
	 * @param creationdate the new creationdate
	 */
	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public Boolean getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(Boolean state) {
		this.state = state;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public UserEntity getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(UserEntity username) {
		this.username = username;
	}
}
