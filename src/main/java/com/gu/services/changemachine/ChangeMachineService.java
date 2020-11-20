package com.gu.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.services.dailies.Daily;

public interface ChangeMachineService {

	public BigDecimal getIncomeTotalMonth();

	public void reset(String sdate);

	public BigDecimal getAwards();

	public ChangeMachineEntity findById(Long idchangemachine);

	public Daily save(ChangeMachineEntity cm);

	public void loadDataTicketServer();

	public List<ChangeMachineEntity> getOperationsTicketServer(Date date);

	public List<ChangeMachineEntity> getOperationsTicketServerBetweenDates(Date from, Date until);

	public List<ChangeMachineEntity> recharges(String datefrom);

	public ChangeMachineTotalEntity getTotal();

	public void entryToVisible(BigDecimal amount);

	void subtractChangeMachineTotal(String ip, BigDecimal amount, String award);

}
