package com.gu.dbaccess.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.gu.util.constants.Constants;

@Entity
@Table(name = "returnmoneyemployees")
public class ReturnMoneyEmployeeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idreturnmoneyemployee")
	private Long idreturnmoneyemployee;

	@ManyToOne
	@JoinColumn(name = "idemployee", referencedColumnName = "idemployee")
	private EmployeeEntity employee;

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

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
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
