package com.gu.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.services.dailies.Daily;

public interface ChangeMachineService {

	public void saveLuckiaAward(ChangeMachineEntity cmachine);

	public BigDecimal getIncomeTotalMonth();

	public void reset();

	public BigDecimal getTotal();

	public BigDecimal getAwardsLuckia();

	public BigDecimal getAwards();

	public ChangeMachineEntity getLastLuckia();

	public Map<String, ?> ticketsByDay(Date date);

	public ChangeMachineEntity findOne(Long idchangemachine);

	public Daily save(ChangeMachineEntity cm);

}
