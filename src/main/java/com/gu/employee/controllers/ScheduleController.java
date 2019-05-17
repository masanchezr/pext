package com.gu.employee.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.forms.Schedule;
import com.gu.admin.forms.WeekForm;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.services.calendar.CalendarService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;

@Controller
public class ScheduleController {

	@Autowired
	private CalendarService calendarService;

	@RequestMapping(value = "/employee/searchschedule")
	public ModelAndView searchschedule() {
		ModelAndView model = new ModelAndView("searchschedulee");
		model.addObject("weekForm", new WeekForm());
		return model;
	}

	@RequestMapping(value = "/employee/resultschedule")
	public ModelAndView resultschedule(WeekForm week, BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sweek = week.getWeek();
		List<Schedule> schedule = calendarService.getSchedule(sweek);
		if (schedule == null || schedule.isEmpty()) {
			model.setViewName("searchschedulee");
			model.addObject("weekForm", week);
			result.rejectValue("week", "noschedule");
		} else {
			List<TimeEntity> times = new ArrayList<>();
			Iterator<Schedule> ischedule = schedule.iterator();
			while (ischedule.hasNext()) {
				times.add(ischedule.next().getTime());
			}
			Set<TimeEntity> settimes = new TreeSet<>();
			Collections.sort(schedule);
			settimes.addAll(times);
			times.clear();
			times.addAll(settimes);
			model.addObject(ConstantsJsp.TIMES, times);
			model.addObject("dates", DateUtil.getDates(sweek));
			model.addObject("schedule", schedule);
			model.addObject("week", sweek);
			model.setViewName("schedulee");
		}
		return model;
	}
}
