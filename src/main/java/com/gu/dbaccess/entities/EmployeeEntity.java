package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idemployee")
	private Long idemployee;

	@Column(name = Constants.NAME)
	private String name;

	@Column(name = Constants.ALIAS)
	private String alias;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = Constants.USERNAME)
	private String username;

	public Long getIdemployee() {
		return idemployee;
	}

	public void setIdemployee(Long idemployee) {
		this.idemployee = idemployee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
}
