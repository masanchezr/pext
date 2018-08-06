package com.gu.services.incomemachines;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.IncomeMachineEntity;
import com.gu.dbaccess.repositories.IncomeMachinesRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;

public class IncomeMachineServiceImpl implements IncomeMachineService {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeMachinesRepository incomemachinesrepository;

	public Daily save(IncomeMachineEntity imachine) {
		imachine.setCreationdate(new Date());
		incomemachinesrepository.save(imachine);
		return dailyService.getDailyEmployee(new Date());
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
		return incomemachinesrepository.searchSumByMonth(from, until);
	}
}
