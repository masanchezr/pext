package com.atmj.gsboot.dbaccess.entities;

import java.math.BigDecimal;

public class CollectionEntity {

	private MachineEntity machine;

	private BigDecimal amount;

	public MachineEntity getMachine() {
		return machine;
	}

	public void setMachine(MachineEntity machine) {
		this.machine = machine;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
