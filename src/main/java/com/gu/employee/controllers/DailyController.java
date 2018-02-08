package com.gu.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.ConstantsJsp;

/**
 * The Class DailyController.
 */
@Controller
public class DailyController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/**
	 * Daily.
	 *
	 * @return the model and view
	 */
	@RequestMapping(value = "/employee/daily")
	public ModelAndView daily() {
		ModelAndView model = new ModelAndView();
		Daily daily = dailyService.getDailyEmployee(new Date());
		if (daily.getFinalamount() == null) {
			model.setViewName(ConstantsJsp.VIEWNOTDAILY);
		} else {
			model.addObject(ConstantsJsp.DAILY, daily);
			model.setViewName(ConstantsJsp.DAILY);
			model.addObject(ConstantsJsp.DATEDAILY, new Date());
		}
		return model;
	}
}
