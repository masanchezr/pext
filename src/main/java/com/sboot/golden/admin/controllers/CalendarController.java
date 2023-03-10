package com.sboot.golden.admin.controllers;

import java.util.List;

import com.sboot.golden.admin.forms.Schedule;
import com.sboot.golden.admin.forms.WeekForm;
import com.sboot.golden.services.calendar.CalendarService;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {

	@Autowired
	private CalendarService calendarService;

	@GetMapping("/admin/searchschedule")
	public ModelAndView searchschedule() {
		ModelAndView model = new ModelAndView("admin/calendar/searchschedule");
		model.addObject(ConstantsViews.MODELWEEKFORM, new WeekForm());
		return model;
	}

	@PostMapping("/admin/resultschedule")
	public ModelAndView resultschedule(WeekForm week, BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sweek = week.getWeek();
		List<Schedule> schedule = calendarService.getSchedule(sweek);
		if (schedule == null || schedule.isEmpty()) {
			model.setViewName("admin/calendar/searchschedule");
			model.addObject(ConstantsViews.MODELWEEKFORM, week);
			result.rejectValue("week", "noschedule");
		} else {
			model.addObject("dates", DateUtil.getDates(sweek));
			model.addObject(ConstantsViews.MODELSCHEDULE, schedule);
			model.addObject("week", sweek);
			model.setViewName("admin/calendar/schedule");
		}
		return model;
	}
}
