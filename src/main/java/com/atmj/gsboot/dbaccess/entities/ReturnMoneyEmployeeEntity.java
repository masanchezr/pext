package com.atmj.gsboot.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.atmj.gsboot.util.constants.Constants;

@Entity
@Table(name = "returnmoneyemployees")
public class ReturnMoneyEmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idreturnmoneyemployee")
	private Long idreturnmoneyemployee;

	@ManyToOne
	@JoinColumn(name = "idemployee", referencedColumnName = "id")
	private UserEntity employee;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = "returndate")
	private Date returndate;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@Column(name = Constants.DESCRIPTION)
	private String description;

	public Long getIdreturnmoneyemployee() {
		return idreturnmoneyemployee;
	}

	public void setIdreturnmoneyemployee(Long idreturnmoneyemployee) {
		this.idreturnmoneyemployee = idreturnmoneyemployee;
	}

	public UserEntity getEmployee() {
		return employee;
	}

	public void setEmployee(UserEntity employee) {
		this.employee = employee;
	}

	public Date getCreationdate() {
		return creationdate;
	}

	public void setCreationdate(Date creationdate) {
		this.creationdate = creationdate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReturndate() {
		return returndate;
	}

	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}
}
