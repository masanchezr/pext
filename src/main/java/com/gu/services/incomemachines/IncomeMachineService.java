package com.gu.services.incomemachines;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.IncomeMachineEntity;
import com.gu.services.dailies.Daily;

public interface IncomeMachineService {

	Daily save(IncomeMachineEntity imachine);

	BigDecimal findIncomeByMonth(String datefrom);

}
