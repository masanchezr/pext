package com.gu.employee.controllers;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;

/**
 * The Class DailyController.
 */
@Controller
public class DailyController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	/** The log. */
	private static Logger log = LoggerFactory.getLogger(DailyController.class);

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
			model.setViewName("notdaily");
		} else {
			log.info(daily.toString());
			model.addObject("daily", daily);
			model.setViewName("daily");
			model.addObject("datedaily", new Date());
		}
		return model;
	}
}
