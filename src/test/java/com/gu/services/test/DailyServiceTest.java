package com.gu.services.test;

import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;

/**
 * The Class DailyServiceTest.
 */
@ExtendWith(SpringExtension.class)
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
		Calendar c = Calendar.getInstance();
		c.set(2018, 9, 30);
		Daily daily = dailyService.getDaily(c.getTime());
		if (daily != null) {
			System.out.println(
					"Numero de operaciones:" + daily.getNumoperations() + " importe final:" + daily.getFinalamount());
		}

	}

	@Test
	public void calculateDailiesTest() {
		Calendar c = Calendar.getInstance();
		c.set(2018, 9, 30);
		dailyService.calculateDailies(c.getTime());
	}
}
