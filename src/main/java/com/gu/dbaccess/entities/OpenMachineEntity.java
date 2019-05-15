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
@Table(name = Constants.OPENMACHINES)
public class OpenMachineEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idopenmachine")
	private Long idopenmachine;

	@Column(name = Constants.DESCRIPTION)
	private String description;

	@Column(name = Constants.CREATIONDATE)
	@CreatedDate
	private Date creationdate;

	@Column(name = Constants.AMOUNT)
	private BigDecimal amount;

	@ManyToOne
	@JoinColumn(name = "idemployee", referencedColumnName = "idemployee")
	private EmployeeEntity employee;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	@ManyToOne
	@JoinColumn(name = "idcause", referencedColumnName = "idcause")
	private CauseEntity cause;

	public Long getIdopenmachine() {
		return idopenmachine;
	}

	public void setIdopenmachine(Long idopenmachine) {
		this.idopenmachine = idopenmachine;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public EmployeeEntity getEmployee() {
		return employee;
	}

	public void setEmployee(EmployeeEntity employee) {
		this.employee = employee;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public CauseEntity getCause() {
		return cause;
	}

	public void setCause(CauseEntity cause) {
		this.cause = cause;
	}

}
