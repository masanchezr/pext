package com.atmj.gsboot.services.returnmoneyemployees;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.atmj.gsboot.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.atmj.gsboot.services.users.User;

public interface ReturnMoneyEmployeeService {

	void savereturn(ReturnMoneyEmployeeEntity returnme);

	BigDecimal findIncomeByMonth(Date from, Date until);

	List<ReturnMoneyEmployeeEntity> findAdvanceByEmployee(User employee);

	void savemoneyadvance(ReturnMoneyEmployeeEntity returnme);

	boolean isAllowedAdvances(ReturnMoneyEmployeeEntity returnme);

}
