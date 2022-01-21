package com.sboot.golden.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sboot.golden.dbaccess.entities.ChangeMachineEntity;
import com.sboot.golden.dbaccess.entities.ChangeMachineTotalEntity;
import com.sboot.golden.dbaccess.entities.CollectionEntity;
import com.sboot.golden.dbaccess.entities.TakeEntity;

public interface ChangeMachineService {

	public BigDecimal getIncomeTotalMonth();

	public void reset(String sdate);

	public BigDecimal getAwards();

	public ChangeMachineEntity findById(Long idchangemachine);

	public void loadDataTicketServer();

	public List<ChangeMachineEntity> getOperationsTicketServer(Date date);

	public List<ChangeMachineEntity> getOperationsTicketServerBetweenDates(Date from, Date until);

	public List<ChangeMachineEntity> recharges(String datefrom);

	public ChangeMachineTotalEntity getTotal();

	public void entryToVisible(BigDecimal amount);

	void subtractChangeMachineTotal(String ip, BigDecimal amount);

	Iterable<TakeEntity> getAllTakings();

	public List<CollectionEntity> manualpayments(Long idtake);

}
