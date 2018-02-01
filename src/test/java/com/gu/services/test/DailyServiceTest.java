package com.gu.services.test;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;

/**
 * The Class DailyServiceTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class DailyServiceTest {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/**
	 * Gets the daily test.
	 *
	 * @return the daily test
	 */
	@Test
	public void getDailyTest() {
		Daily daily = dailyService.getDaily(new Date());
		if (daily != null) {
			System.out.println(
					"Numero de operaciones:" + daily.getNumoperations() + " importe final:" + daily.getFinalamount());
		}

	}

	@Test
	public void calculateDailiesTest() {
		Calendar c = Calendar.getInstance();
		c.set(2017, 11, 23);
		dailyService.calculateDailies(c.getTime());
	}
}
