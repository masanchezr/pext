package com.sboot.golden.services.incomemachines;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.IncomeMachineEntity;
import com.sboot.golden.dbaccess.repositories.IncomeMachinesRepository;
import com.sboot.golden.util.date.DateUtil;

@Service
public class IncomeMachineServiceImpl implements IncomeMachineService {

	@Autowired
	private IncomeMachinesRepository incomemachinesrepository;

	public void save(IncomeMachineEntity imachine) {
		imachine.setCreationdate(new DateUtil().getNow());
		incomemachinesrepository.save(imachine);
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
