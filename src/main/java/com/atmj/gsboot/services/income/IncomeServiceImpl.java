package com.atmj.gsboot.services.income;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.BarDrinkEntity;
import com.atmj.gsboot.dbaccess.repositories.BarDrinksRepository;
import com.atmj.gsboot.util.date.DateUtil;

@Service
public class IncomeServiceImpl implements IncomeService {

	@Autowired
	private BarDrinksRepository incomerepository;

	public void save(BarDrinkEntity income) {
		income.setCreationdate(new DateUtil().getNow());
		incomerepository.save(income);
	}

	public BigDecimal findIncomeByMonth(Date from, Date until) {
		return incomerepository.searchSumByMonth(from, until);
	}
}
