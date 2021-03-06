package com.sboot.golden.services.incomeluckia;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.IncomeLuckiaEntity;
import com.sboot.golden.dbaccess.repositories.IncomeLuckiaRepository;
import com.sboot.golden.util.date.DateUtil;

@Service
public class IncomeLuckiaServiceImpl implements IncomeLuckiaService {

	@Autowired
	private IncomeLuckiaRepository incomeluckiarepository;

	public void save(IncomeLuckiaEntity iluckia) {
		iluckia.setCreationdate(new DateUtil().getNow());
		incomeluckiarepository.save(iluckia);
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
		return incomeluckiarepository.searchSumByMonth(from, until);
	}

}
