package com.sboot.golden.services.incomemachines;

import java.math.BigDecimal;

import com.sboot.golden.dbaccess.entities.IncomeMachineEntity;

public interface IncomeMachineService {

	void save(IncomeMachineEntity imachine);

	BigDecimal findIncomeByMonth(String datefrom);

}
