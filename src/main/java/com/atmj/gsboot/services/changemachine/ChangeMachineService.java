package com.atmj.gsboot.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.atmj.gsboot.dbaccess.entities.ChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineTotalEntity;
import com.atmj.gsboot.dbaccess.entities.CollectionEntity;
import com.atmj.gsboot.dbaccess.entities.TakeEntity;

public interface ChangeMachineService {

	public BigDecimal getIncomeTotalMonth();

	public void reset(String sdate);

	public BigDecimal getAwards();

	public ChangeMachineEntity findById(Long idchangemachine);

	public void loadDataTicketServer();

	public List<ChangeMachineEntity> getOperationsTicketServer(Date date);

	public List<ChangeMachineEntity> getOperationsTicketServerBetweenDates(Date from, Date until);

	public ChangeMachineTotalEntity getTotal();

	public void entryToVisible(BigDecimal amount);

	Iterable<TakeEntity> getAllTakings();

	public List<CollectionEntity> manualpayments(Long idtake);

	public List<ChangeMachineEntity> recharges(Date from, Date takedate);

	void subtractChangeMachineTotal(String ip, String award, BigDecimal amount);

}
