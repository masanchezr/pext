package com.gu.services.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.services.changemachine.ChangeMachineService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
class ChangeMachineServiceTest {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Test
	void getAwardsTest() {
		Assertions.assertNotNull(changeMachineService.getAwards());
	}

	@Test
	void getIncomeTotalMonthTest() {
		Assertions.assertNotNull(changeMachineService.getIncomeTotalMonth());
	}
}
