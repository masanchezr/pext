package com.bar.services.income;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bar.dbaccess.entities.IncomeEntity;
import com.bar.dbaccess.repositories.IncomeRepository;
import com.bar.services.dailies.Daily;
import com.bar.services.dailies.DailyService;
import com.bar.util.date.DateUtil;

public class IncomeServiceImpl implements IncomeService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeRepository incomerepository;

	public Daily save(IncomeEntity income) {
		income.setCreationdate(new Date());
		incomerepository.save(income);
		return dailyService.getDailyEmployee(new Date());
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
