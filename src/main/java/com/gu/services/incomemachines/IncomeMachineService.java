package com.gu.services.incomemachines;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.IncomeMachineEntity;

public interface IncomeMachineService {

	void save(IncomeMachineEntity imachine);

	BigDecimal findIncomeByMonth(String datefrom);

}
