package com.atmj.gsboot.services.incomesportsbets;

import java.math.BigDecimal;
import java.util.Date;

import com.atmj.gsboot.dbaccess.entities.IncomeSportsBetsEntity;

public interface IncomeSportsBetsService {

	void save(IncomeSportsBetsEntity isportsbets);

	BigDecimal findIncomeByMonth(Date from, Date until);

}
