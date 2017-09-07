package com.gu.dbaccess.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "operationsnotallowed")
public class OperationNotAllowedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
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
