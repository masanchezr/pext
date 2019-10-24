package com.gu.services.income;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.BarDrinkEntity;

public interface IncomeService {

	public void save(BarDrinkEntity income);

	public BigDecimal findIncomeByMonth(String month);

}
