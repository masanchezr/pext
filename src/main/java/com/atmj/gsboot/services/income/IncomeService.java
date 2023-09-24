package com.atmj.gsboot.services.income;

import java.math.BigDecimal;

import com.atmj.gsboot.dbaccess.entities.BarDrinkEntity;

public interface IncomeService {

	public void save(BarDrinkEntity income);

	public BigDecimal findIncomeByMonth(String month);

}
