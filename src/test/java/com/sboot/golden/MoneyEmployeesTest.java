package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.services.returnmoneyemployees.ReturnMoneyEmployeeService;

@SpringBootTest
class MoneyEmployeesTest {

	@Autowired
	private ReturnMoneyEmployeeService moneyemployeeservice;

	@Test
	void isAllowedAdvancesTest() {
		ReturnMoneyEmployeeEntity returnme = new ReturnMoneyEmployeeEntity();
		UserEntity employee = new UserEntity();
		employee.setId(2L);
		returnme.setEmployee(employee);
		returnme.setAmount(new BigDecimal(200));
		assertTrue(moneyemployeeservice.isAllowedAdvances(returnme));
	}
}
