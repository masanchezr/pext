package com.gu.services.returnmoneyemployees;

import java.math.BigDecimal;
import java.util.List;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;

public interface ReturnMoneyEmployeeService {

	void savereturn(ReturnMoneyEmployeeEntity returnme);

	BigDecimal findIncomeByMonth(String datefrom);

	List<ReturnMoneyEmployeeEntity> findAdvanceByEmployee(EmployeeEntity employee);

	void savemoneyadvance(ReturnMoneyEmployeeEntity returnme);

	boolean isAllowedAdvances(ReturnMoneyEmployeeEntity returnme);

}
