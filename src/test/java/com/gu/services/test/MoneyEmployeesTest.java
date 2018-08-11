package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.services.returnmoneyemployees.ReturnMoneyEmployeeService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class MoneyEmployeesTest {

	@Autowired
	private ReturnMoneyEmployeeService moneyemployeeservice;

	@Test
	public void isAllowedAdvancesTest() {
		ReturnMoneyEmployeeEntity returnme = new ReturnMoneyEmployeeEntity();
		EmployeeEntity employee = new EmployeeEntity();
		employee.setIdemployee(2L);
		returnme.setEmployee(employee);
		returnme.setAmount(new BigDecimal(200));
		System.out.println(moneyemployeeservice.isAllowedAdvances(returnme));
	}
}
