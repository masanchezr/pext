package com.gu.services.returnmoneyemployees;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.services.dailies.Daily;

public interface ReturnMoneyEmployeeService {

	Daily save(ReturnMoneyEmployeeEntity returnme);

	BigDecimal findIncomeByMonth(String datefrom);

}
