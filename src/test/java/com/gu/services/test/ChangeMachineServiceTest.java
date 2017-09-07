package com.gu.services.test;

import java.math.BigDecimal;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.services.changemachine.ChangeMachineService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class ChangeMachineServiceTest {

	@Autowired
	private ChangeMachineService changeMachineService;

	private static final Logger logger = Logger.getLogger(ChangeMachineServiceTest.class);

	@Test
	public void getAwardsTest() {
		BigDecimal awards = changeMachineService.getAwards();
		logger.trace(awards);
	}

	@Test
	public void getAwardsLuckiaTest() {
		BigDecimal awards = changeMachineService.getAwardsLuckia();
		logger.trace(awards);
	}
}
