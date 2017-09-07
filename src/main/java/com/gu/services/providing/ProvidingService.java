package com.gu.services.providing;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.ProvidingEntity;

public interface ProvidingService {

	public void save(ProvidingEntity providing);

	public BigDecimal searchTotal();

}
