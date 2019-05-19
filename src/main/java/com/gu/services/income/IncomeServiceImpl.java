package com.gu.services.income;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.dbaccess.repositories.BarDrinksRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;

public class IncomeServiceImpl implements IncomeService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private BarDrinksRepository incomerepository;

	public Daily save(BarDrinkEntity income) {
		income.setCreationdate(new DateUtil().getNow());
		incomerepository.save(income);
		return dailyService.getDailyEmployee(new DateUtil().getNow());
	}

	public BigDecimal findIncomeByMonth(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		return incomerepository.searchSumByMonth(from, until);
	}
}
