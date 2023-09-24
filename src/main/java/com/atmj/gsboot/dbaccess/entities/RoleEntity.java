package com.atmj.gsboot.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.atmj.dbaccess.entities.AuthorityEntity;

@Entity
@Table(name = "authoritiesusers")
public class RoleEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long idrole;

	@Column(name = "userid")
	private Long useride;

	@Column(name = "authorityid")
	private AuthorityEntity role;

	public Long getIdrole() {
		return idrole;
	}

	public Long getUseride() {
		return useride;
	}

	public void setUseride(Long useride) {
		this.useride = useride;
	}

	public AuthorityEntity getRole() {
		return role;
	}

	public void setRole(AuthorityEntity role) {
		this.role = role;
	}

	public void setIdrole(Long idrole) {
		this.idrole = idrole;
	}
}
