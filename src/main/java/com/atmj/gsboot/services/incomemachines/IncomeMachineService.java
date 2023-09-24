package com.atmj.gsboot.services.incomemachines;

import java.math.BigDecimal;

import com.atmj.gsboot.dbaccess.entities.IncomeMachineEntity;

public interface IncomeMachineService {

	void save(IncomeMachineEntity imachine);

	BigDecimal findIncomeByMonth(String datefrom);

}
