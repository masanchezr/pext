package com.atmj.gsboot.services.incomesportsbets;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.IncomeSportsBetsEntity;
import com.atmj.gsboot.dbaccess.repositories.IncomeSportsBetsRepository;
import com.atmj.gsboot.util.date.DateUtil;

@Service
public class IncomeSportsBetsServiceImpl implements IncomeSportsBetsService {

	@Autowired
	private IncomeSportsBetsRepository incomesportsbetsrepository;

	public void save(IncomeSportsBetsEntity isportsbets) {
		isportsbets.setCreationdate(new DateUtil().getNow());
		incomesportsbetsrepository.save(isportsbets);
	}

	public BigDecimal findIncomeByMonth(Date from, Date until) {
		return incomesportsbetsrepository.searchSumByMonth(from, until);
	}

}
