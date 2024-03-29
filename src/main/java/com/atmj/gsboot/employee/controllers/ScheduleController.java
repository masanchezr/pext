package com.atmj.gsboot.employee.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.admin.forms.Schedule;
import com.atmj.gsboot.admin.forms.WeekForm;
import com.atmj.gsboot.dbaccess.entities.TimeEntity;
import com.atmj.gsboot.services.calendar.CalendarService;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

@Controller
public class ScheduleController {

	@Autowired
	private CalendarService calendarService;

	@GetMapping("/employee/searchschedule")
	public ModelAndView searchschedule() {
		ModelAndView model = new ModelAndView("employee/calendar/searchschedule");
		model.addObject("weekForm", new WeekForm());
		return model;
	}

	@GetMapping("/employee/resultschedule")
	public ModelAndView resultschedule(WeekForm week, BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sweek = week.getWeek();
		List<Schedule> schedule = calendarService.getSchedule(sweek);
		if (schedule == null || schedule.isEmpty()) {
			model.setViewName("employee/calendar/searchschedule");
			model.addObject("weekForm", week);
			result.rejectValue("week", "noschedule");
		} else {
			List<TimeEntity> times = new ArrayList<>();
			Iterator<Schedule> ischedule = schedule.iterator();
			while (ischedule.hasNext()) {
				times.add(ischedule.next().getTime());
			}
			Set<TimeEntity> settimes = new TreeSet<>();
			settimes.addAll(times);
			times.clear();
			times.addAll(settimes);
			model.addObject(ConstantsViews.TIMES, times);
			model.addObject("dates", DateUtil.getDates(sweek));
			model.addObject("schedule", schedule);
			model.addObject("week", sweek);
			model.setViewName("employee/calendar/schedule");
		}
		return model;
	}
}
