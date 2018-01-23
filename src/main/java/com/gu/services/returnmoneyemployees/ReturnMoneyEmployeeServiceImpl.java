package com.gu.services.returnmoneyemployees;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.dbaccess.repositories.ReturnMoneyEmployeesRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;

public class ReturnMoneyEmployeeServiceImpl implements ReturnMoneyEmployeeService {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private ReturnMoneyEmployeesRepository returnMoneyEmployeesRepository;

	public Daily savereturn(ReturnMoneyEmployeeEntity returnme) {
		returnme = returnMoneyEmployeesRepository.findById(returnme.getIdreturnmoneyemployee()).get();
		returnme.setReturndate(new Date());
		returnMoneyEmployeesRepository.save(returnme);
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
		return returnMoneyEmployeesRepository.searchSumByMonth(from, until);
	}

	public List<ReturnMoneyEmployeeEntity> findAdvanceByEmployee(EmployeeEntity employee) {
		return returnMoneyEmployeesRepository.findByEmployeeAndReturndateIsNull(employee);
	}

	public Daily savemoneyadvance(ReturnMoneyEmployeeEntity returnme) {
		returnme.setCreationdate(new Date());
		returnMoneyEmployeesRepository.save(returnme);
		return dailyService.getDailyEmployee(new Date());
	}

}
