package com.sboot.golden.employee.forms;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import com.sboot.golden.dbaccess.entities.UserEntity;

public class ReturnMoneyEmployee {

	private Long idreturnmoneyemployee;

	private UserEntity employee;

	@CreatedDate
	private Date creationdate;

	private Date returndate;

	private BigDecimal amount;

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
