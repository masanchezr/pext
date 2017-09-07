package com.gu.services.income;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.services.dailies.Daily;

public interface IncomeService {

	public Daily save(BarDrinkEntity income);

	public BigDecimal findIncomeByMonth(String month);

}
