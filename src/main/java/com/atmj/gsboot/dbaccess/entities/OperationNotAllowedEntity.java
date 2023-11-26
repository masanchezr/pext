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
@Table(name = "operationsnotallowed")
public class OperationNotAllowedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idoperationnotallowed")
	private Long idoperationnotallowed;

	@ManyToOne
	@JoinColumn(name = "idmachine", referencedColumnName = "idmachine")
	private MachineEntity machine;

	@ManyToOne
	@JoinColumn(name = "idaward", referencedColumnName = "idaward")
	private AwardEntity award;

	/** The pay. */
	@ManyToOne
	@JoinColumn(name = "IDPAYMENT")
	private PaymentEntity pay;

	public Long getIdoperationnotallowed() {
		return idoperationnotallowed;
	}

	public void setIdoperationnotallowed(Long idoperationnotallowed) {
		this.idoperationnotallowed = idoperationnotallowed;
	}

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public AwardEntity getAward() {
		return award;
	}

	public void setAward(AwardEntity award) {
		this.award = award;
	}

	public PaymentEntity getPay() {
		return pay;
	}

	public void setPay(PaymentEntity pay) {
		this.pay = pay;
	}

}
