package com.sboot.golden.services.returnmoneyemployees;

import java.math.BigDecimal;
import java.util.List;

import com.sboot.golden.dbaccess.entities.EmployeeEntity;
import com.sboot.golden.dbaccess.entities.ReturnMoneyEmployeeEntity;

public interface ReturnMoneyEmployeeService {

	void savereturn(ReturnMoneyEmployeeEntity returnme);

	BigDecimal findIncomeByMonth(String datefrom);

	List<ReturnMoneyEmployeeEntity> findAdvanceByEmployee(EmployeeEntity employee);

	void savemoneyadvance(ReturnMoneyEmployeeEntity returnme);

	boolean isAllowedAdvances(ReturnMoneyEmployeeEntity returnme);

}
