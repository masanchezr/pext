package com.atmj.gsboot.services.incomemachines;

import java.math.BigDecimal;
import java.util.Date;

import com.atmj.gsboot.dbaccess.entities.IncomeMachineEntity;

public interface IncomeMachineService {

	void save(IncomeMachineEntity imachine);

	BigDecimal findIncomeByMonth(Date from, Date until);

}
