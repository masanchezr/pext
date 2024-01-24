package com.atmj.gsboot.services.income;

import java.math.BigDecimal;
import java.util.Date;

import com.atmj.gsboot.dbaccess.entities.BarDrinkEntity;

public interface IncomeService {

	public void save(BarDrinkEntity income);

	public BigDecimal findIncomeByMonth(Date from, Date until);

}
