package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;

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
	@GetMapping("/employee/daily")
	public ModelAndView daily() {
		ModelAndView model = new ModelAndView();
		Daily daily = dailyService.getDailyEmployee();
		if (daily.getFinalamount() == null) {
			model.setViewName("employee/daily/notdaily");
		} else {
			model.addObject(ConstantsJsp.DAILY, daily);
			model.setViewName("employee/daily/daily");
			model.addObject(ConstantsJsp.DATEDAILY, new DateUtil().getNow());
		}
		return model;
	}
}
