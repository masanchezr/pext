package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.services.changemachine.ChangeMachineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class ChangeMachineServiceTest {

	@Autowired
	private ChangeMachineService changeMachineService;

	private static final Logger logger = LoggerFactory.getLogger(ChangeMachineServiceTest.class);

	@Test
	public void getAwardsTest() {
		BigDecimal awards = changeMachineService.getAwards();
		logger.trace(awards.toString());
	}

	@Test
	public void getIncomeTotalMonthTest() {
		changeMachineService.getIncomeTotalMonth();
	}
}
