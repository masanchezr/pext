package com.gu.admin.controllers;

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
import com.gu.admin.forms.ScheduleForm;
import com.gu.admin.forms.WeekForm;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.services.calendar.CalendarService;
import com.gu.services.employees.EmployeeService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;

@Controller
public class CalendarController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CalendarService calendarService;

	@RequestMapping(value = "/admin/calendar")
	public ModelAndView calendar(WeekForm week) {
		ModelAndView model = new ModelAndView("calendar");
		ScheduleForm schedule = new ScheduleForm();
		String sweek = week.getWeek();
		List<Schedule> lscheduleform = calendarService.getSchedule(sweek);
		model.addObject(ConstantsJsp.EMPLOYEES, employeeService.allEmployeesActives());
		model.addObject("week", sweek);
		model.addObject("dates", DateUtil.getDates(sweek));
		if (lscheduleform == null || lscheduleform.isEmpty()) {
			model.addObject(ConstantsJsp.TIMES, calendarService.getTimesActive());
			schedule.setSchedule(lscheduleform);
		} else {
			List<TimeEntity> times = new ArrayList<TimeEntity>();
			Set<TimeEntity> settimes = new TreeSet<TimeEntity>();
			Collections.sort(lscheduleform);
			times.addAll(calendarService.getTimesActive());
			settimes.addAll(times);
			times.clear();
			times.addAll(settimes);
			model.addObject(ConstantsJsp.TIMES, times);
			schedule.setSchedule(lscheduleform);
		}
		model.addObject("scheduleForm", schedule);
		return model;
	}

	@RequestMapping(value = "/admin/newcalendar")
	public ModelAndView newcalendar() {
		ModelAndView model = new ModelAndView("newcalendar");
		model.addObject("weekForm", new WeekForm());
		return model;
	}

	@RequestMapping(value = "/admin/saveschedule")
	public ModelAndView saveSchedule(ScheduleForm sform) {
		ModelAndView model = new ModelAndView("successadmin");
		model.addObject("schedule", calendarService.save(sform.getSchedule()));
		// model.addObject(ConstantsJsp.TIMES, calendarService.getTimesActive());
		return model;
	}

	@RequestMapping(value = "/admin/searchschedule")
	public ModelAndView searchschedule() {
		ModelAndView model = new ModelAndView("searchschedule");
		model.addObject("weekForm", new WeekForm());
		return model;
	}

	@RequestMapping(value = "/admin/resultschedule")
	public ModelAndView resultschedule(WeekForm week, BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sweek = week.getWeek();
		List<Schedule> schedule = calendarService.getSchedule(sweek);
		if (schedule == null || schedule.isEmpty()) {
			model.setViewName("searchschedule");
			model.addObject("weekForm", week);
			result.rejectValue("week", "noschedule");
		} else {
			List<TimeEntity> times = new ArrayList<TimeEntity>();
			Iterator<Schedule> ischedule = schedule.iterator();
			while (ischedule.hasNext()) {
				times.add(ischedule.next().getTime());
			}
			Set<TimeEntity> settimes = new TreeSet<TimeEntity>();
			Collections.sort(schedule);
			settimes.addAll(times);
			times.clear();
			times.addAll(settimes);
			model.addObject(ConstantsJsp.TIMES, times);
			model.addObject("dates", DateUtil.getDates(sweek));
			model.addObject("schedule", schedule);
			model.addObject("week", sweek);
			model.setViewName("schedule");
		}
		return model;
	}
}
