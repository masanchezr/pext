package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.services.changemachine.ChangeMachineService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class ChangeMachineServiceTest {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Test
	public void getAwardsTest() {
		BigDecimal awards = changeMachineService.getAwards();
		System.out.println(awards.toString());
	}

	@Test
	public void getIncomeTotalMonthTest() {
		System.out.println(changeMachineService.getIncomeTotalMonth());
	}

	@Test
	public void loadDataTicketServerTest() {
		changeMachineService.loadDataTicketServer();
	}
}
