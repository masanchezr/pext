package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.services.returnmoneyemployees.ReturnMoneyEmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
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
