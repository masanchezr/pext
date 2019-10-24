package com.gu.services.income;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.dbaccess.repositories.BarDrinksRepository;
import com.gu.util.date.DateUtil;

public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private BarDrinksRepository incomerepository;

	public void save(BarDrinkEntity income) {
		income.setCreationdate(new DateUtil().getNow());
		incomerepository.save(income);
	}

	public BigDecimal findIncomeByMonth(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		return incomerepository.searchSumByMonth(from, until);
	}
}
