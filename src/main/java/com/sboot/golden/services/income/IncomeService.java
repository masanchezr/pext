package com.sboot.golden.services.income;

import java.math.BigDecimal;

import com.sboot.golden.dbaccess.entities.BarDrinkEntity;

public interface IncomeService {

	public void save(BarDrinkEntity income);

	public BigDecimal findIncomeByMonth(String month);

}
