package com.bar.services.income;

import java.math.BigDecimal;

import com.bar.dbaccess.entities.IncomeEntity;
import com.bar.services.dailies.Daily;

public interface IncomeService {

	public Daily save(IncomeEntity income);

	public BigDecimal findIncomeByMonth(String month);

}
