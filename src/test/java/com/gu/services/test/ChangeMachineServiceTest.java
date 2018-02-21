package com.gu.services.test;

import java.math.BigDecimal;
import java.util.List;

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
	public void findLostNumbersTest() {
		List<Long> ln = changeMachineService.findLostNumbers();
		for (Long long1 : ln) {
			System.out.println(long1.toString());
		}
	}

	@Test
	public void loadDataTicketServerTest() {
		changeMachineService.loadDataTicketServer();
	}
}
