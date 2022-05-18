package com.sboot.golden.services.dailies;

import java.util.Date;

import com.sboot.golden.dbaccess.entities.ChangeMachineEntity;

/**
 * The Interface DailyService.
 */
public interface DailyService {

	/**
	 * Gets the daily.
	 *
	 * @param date      the date
	 * @param place     the place
	 * @param ipAddress
	 * @return the daily
	 */
	public Daily getDaily(Date date);

	public void calculateDailies(Date date);

	public Daily getDailyEmployee();

	Daily save(ChangeMachineEntity cm);
}
