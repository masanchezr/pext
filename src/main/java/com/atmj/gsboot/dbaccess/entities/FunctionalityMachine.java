package com.atmj.gsboot.dbaccess.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "functionalitiesmachine")
public class FunctionalityMachine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idfunctionalitymachine")
	private Long idfunctionalitymachine;

	@ManyToOne
	@JoinColumn(name = "idfunctionality", referencedColumnName = "idfuncionality")
	private FunctionalityEntity functionality;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	public Long getIdfunctionalitymachine() {
		return idfunctionalitymachine;
	}

	public void setIdfunctionalitymachine(Long idfunctionalitymachine) {
		this.idfunctionalitymachine = idfunctionalitymachine;
	}

	public FunctionalityEntity getFunctionality() {
		return functionality;
	}

	public void setFunctionality(FunctionalityEntity funcionality) {
		this.functionality = funcionality;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

}
