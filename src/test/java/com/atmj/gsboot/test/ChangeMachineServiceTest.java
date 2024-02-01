package com.atmj.gsboot.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.services.changemachine.ChangeMachineService;

@SpringBootTest
class ChangeMachineServiceTest {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Test
	void getAwardsTest() {
		Assertions.assertNotNull(changeMachineService.getAwards());
	}

	@Test
	void getIncomeTotalMonthTest() {
		Assertions.assertNull(changeMachineService.getIncomeTotalMonth());
	}

	@Test
	void rechargesTest() {
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		c.set(Calendar.YEAR, 2022);
		c.set(Calendar.MONTH, Calendar.DECEMBER);
		c.set(Calendar.DAY_OF_MONTH, 30);
		Assertions.assertNotNull(changeMachineService.recharges(c.getTime(), now));
	}

	@Test
	void loadDataTicketServerTest() {
		changeMachineService.loadDataTicketServer();
	}
}
