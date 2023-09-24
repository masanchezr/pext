package com.atmj.gsboot.dbaccess.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.atmj.dbaccess.entities.AuthorityEntity;
import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5567159956031384263L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "fullname")
	private String name;

	@Column(name = Constants.ALIAS)
	private String alias;

	@Column(name = "dni")
	private String dni;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "authoritiesusers", joinColumns = @JoinColumn(name = "userid"), inverseJoinColumns = @JoinColumn(name = "authorityid"))
	private Set<AuthorityEntity> authority;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Set<AuthorityEntity> getAuthority() {
		return authority;
	}

	public void setAuthority(Set<AuthorityEntity> authority) {
		this.authority = authority;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}
