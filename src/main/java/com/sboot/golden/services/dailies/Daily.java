package com.sboot.golden.services.dailies;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sboot.golden.dbaccess.entities.BarDrinkEntity;
import com.sboot.golden.dbaccess.entities.ChangeMachineEntity;
import com.sboot.golden.dbaccess.entities.EntryMoneyEntity;
import com.sboot.golden.dbaccess.entities.GratificationEntity;
import com.sboot.golden.dbaccess.entities.IncomeLuckiaEntity;
import com.sboot.golden.dbaccess.entities.IncomeMachineEntity;
import com.sboot.golden.dbaccess.entities.OperationEntity;
import com.sboot.golden.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.sboot.golden.dbaccess.entities.TPVEntity;

/**
 * The Class Daily. Clase para mostrar en pantalla las operaciones del parte.
 */
public class Daily {

	/** The finalamount. */
	private BigDecimal finalamount;

	private int numoperations;

	private List<OperationEntity> operations;

	private List<EntryMoneyEntity> entriesMoney;

	private List<BarDrinkEntity> income;

	private List<IncomeLuckiaEntity> incomeluckia;

	private List<IncomeMachineEntity> incomemachines;

	private List<ReturnMoneyEmployeeEntity> returns;

	private Date date;

	private List<GratificationEntity> gratifications;

	private List<TPVEntity> tpvs;

	private List<ChangeMachineEntity> listchangemachine;

	private List<ReturnMoneyEmployeeEntity> moneyadvance;

	public BigDecimal getFinalamount() {
		return finalamount;
	}

	public void setFinalamount(BigDecimal finalamount) {
		this.finalamount = finalamount;
	}

	public List<OperationEntity> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationEntity> operations) {
		this.operations = operations;
	}

	public int getNumoperations() {
		return numoperations;
	}

	public void setNumoperations(int numoperations) {
		this.numoperations = numoperations;
	}

	public List<EntryMoneyEntity> getEntriesMoney() {
		return entriesMoney;
	}

	public void setEntriesMoney(List<EntryMoneyEntity> entriesMoney) {
		this.entriesMoney = entriesMoney;
	}

	public List<BarDrinkEntity> getIncome() {
		return income;
	}

	public void setIncome(List<BarDrinkEntity> income) {
		this.income = income;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<IncomeLuckiaEntity> getIncomeluckia() {
		return incomeluckia;
	}

	public void setIncomeluckia(List<IncomeLuckiaEntity> incomeluckia) {
		this.incomeluckia = incomeluckia;
	}

	public List<IncomeMachineEntity> getIncomemachines() {
		return incomemachines;
	}

	public void setIncomemachines(List<IncomeMachineEntity> incomemachines) {
		this.incomemachines = incomemachines;
	}

	public List<ReturnMoneyEmployeeEntity> getReturns() {
		return returns;
	}

	public void setReturns(List<ReturnMoneyEmployeeEntity> returns) {
		this.returns = returns;
	}

	public void setGratifications(List<GratificationEntity> gratifications) {
		this.gratifications = gratifications;
	}

	public List<GratificationEntity> getGratifications() {
		return gratifications;
	}

	public void setTpvs(List<TPVEntity> tpvs) {
		this.tpvs = tpvs;
	}

	public List<TPVEntity> getTpvs() {
		return tpvs;
	}

	public List<ChangeMachineEntity> getListchangemachine() {
		return listchangemachine;
	}

	public void setListchangemachine(List<ChangeMachineEntity> listchangemachine) {
		this.listchangemachine = listchangemachine;
	}

	public List<ReturnMoneyEmployeeEntity> getMoneyadvance() {
		return moneyadvance;
	}

	public void setMoneyadvance(List<ReturnMoneyEmployeeEntity> moneyadvance) {
		this.moneyadvance = moneyadvance;
	}
}
