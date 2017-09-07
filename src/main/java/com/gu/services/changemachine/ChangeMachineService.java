package com.gu.services.changemachine;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.ChangeMachineEntity;

public interface ChangeMachineService {

	public void saveLuckiaAward(ChangeMachineEntity cmachine);

	public BigDecimal getIncomeTotalMonth();

	public void reset();

	public BigDecimal getTotal();

	public BigDecimal getAwardsLuckia();

	public BigDecimal getAwards();

	public ChangeMachineEntity getLastLuckia();

}
