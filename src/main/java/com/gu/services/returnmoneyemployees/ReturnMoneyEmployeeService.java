package com.gu.services.returnmoneyemployees;

import java.math.BigDecimal;
import java.util.List;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.services.dailies.Daily;

public interface ReturnMoneyEmployeeService {

	Daily savereturn(ReturnMoneyEmployeeEntity returnme);

	BigDecimal findIncomeByMonth(String datefrom);

	List<ReturnMoneyEmployeeEntity> findAdvanceByEmployee(EmployeeEntity employee);

	Daily savemoneyadvance(ReturnMoneyEmployeeEntity returnme);

	boolean isAllowedAdvances(ReturnMoneyEmployeeEntity returnme);

}
