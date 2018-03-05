package com.gu.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.services.dailies.Daily;

public interface ChangeMachineService {

	public BigDecimal getIncomeTotalMonth();

	public void reset();

	public BigDecimal getTotal();

	public BigDecimal getAwards();

	public Map<String, Object> ticketsByDay(Date date);

	public ChangeMachineEntity findById(Long idchangemachine);

	public Daily save(ChangeMachineEntity cm);

	public void loadDataTicketServer();

	public List<ChangeMachineEntity> getOperationsTicketServer(Date date);

	public List<ChangeMachineEntity> getOperationsTicketServerBetweenDates(Date from, Date until);

	public List<ChangeMachineEntity> recharges(String datefrom);

}
