package com.atmj.gsboot.boss.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.atmj.gsboot.admin.forms.Schedule;
import com.atmj.gsboot.admin.forms.ScheduleForm;
import com.atmj.gsboot.admin.forms.WeekForm;
import com.atmj.gsboot.dbaccess.entities.TimeEntity;
import com.atmj.gsboot.services.calendar.CalendarService;
import com.atmj.gsboot.services.users.UserService;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarBossController {

	@Autowired
	private CalendarService calendarService;

	@Autowired
	private UserService employeeService;

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
			model.addObject("dates", DateUtil.getDates(sweek));
			model.addObject("schedule", schedule);
			model.addObject("week", sweek);
			model.setViewName("boss/calendar/schedule");
		}
		return model;
	}

	@PostMapping("/calendar")
	public ModelAndView calendar(WeekForm week) {
		ModelAndView model = new ModelAndView("admin/calendar/calendar");
		ScheduleForm schedule = new ScheduleForm();
		String sweek = week.getWeek();
		List<Schedule> lscheduleform = calendarService.getSchedule(sweek);
		model.addObject(ConstantsViews.EMPLOYEES, employeeService.allUsersEnabled());
		model.addObject("week", sweek);
		model.addObject("dates", DateUtil.getDates(sweek));
		if (lscheduleform == null || lscheduleform.isEmpty()) {
			model.addObject(ConstantsViews.TIMES, calendarService.getTimesActive());
			schedule.setSchedule(lscheduleform);
		} else {
			List<TimeEntity> times = new ArrayList<>();
			Set<TimeEntity> settimes = new TreeSet<>();
			times.addAll(calendarService.getTimesActive());
			settimes.addAll(times);
			times.clear();
			times.addAll(settimes);
			model.addObject(ConstantsViews.TIMES, times);
			schedule.setSchedule(lscheduleform);
		}
		model.addObject("scheduleForm", schedule);
		return model;
	}

	@GetMapping("/newcalendar")
	public ModelAndView newcalendar() {
		ModelAndView model = new ModelAndView("admin/calendar/searchweek");
		model.addObject(ConstantsViews.MODELWEEKFORM, new WeekForm());
		return model;
	}

	@PostMapping("/saveschedule")
	public ModelAndView saveSchedule(ScheduleForm sform) {
		ModelAndView model = new ModelAndView("admin/success");
		model.addObject(ConstantsViews.MODELSCHEDULE, calendarService.save(sform.getSchedule()));
		return model;
	}
}
