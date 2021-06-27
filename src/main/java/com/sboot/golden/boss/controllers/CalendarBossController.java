package com.sboot.golden.boss.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.admin.forms.Schedule;
import com.sboot.golden.admin.forms.WeekForm;
import com.sboot.golden.dbaccess.entities.TimeEntity;
import com.sboot.golden.services.calendar.CalendarService;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

@Controller
public class CalendarBossController {

	@Autowired
	private CalendarService calendarService;

	@GetMapping("/searchschedule")
	public ModelAndView searchschedule() {
		ModelAndView model = new ModelAndView("boss/calendar/searchschedule");
		model.addObject("weekForm", new WeekForm());
		return model;
	}

	@PostMapping("/resultschedule")
	public ModelAndView resultschedule(WeekForm week, BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sweek = week.getWeek();
		List<Schedule> schedule = calendarService.getSchedule(sweek);
		if (schedule == null || schedule.isEmpty()) {
			model.setViewName("boss/calendar/searchschedule");
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
			model.setViewName("boss/calendar/schedule");
		}
		return model;
	}
}
