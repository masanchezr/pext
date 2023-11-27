package com.atmj.gsboot.test;

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
	void loadDataTicketServerTest() {
		changeMachineService.loadDataTicketServer();
	}
}