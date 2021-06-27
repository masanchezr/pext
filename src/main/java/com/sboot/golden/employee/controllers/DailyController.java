package com.sboot.golden.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.services.dailies.Daily;
import com.sboot.golden.services.dailies.DailyService;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

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
			model.addObject(ConstantsViews.DAILY, daily);
			model.setViewName("employee/daily/daily");
			model.addObject(ConstantsViews.DATEDAILY, new DateUtil().getNow());
		}
		return model;
	}
}
