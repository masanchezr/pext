package com.sboot.golden.admin.controllers;

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
import com.sboot.golden.admin.forms.ScheduleForm;
import com.sboot.golden.admin.forms.WeekForm;
import com.sboot.golden.dbaccess.entities.TimeEntity;
import com.sboot.golden.services.calendar.CalendarService;
import com.sboot.golden.services.employees.EmployeeService;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

@Controller
public class CalendarController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CalendarService calendarService;

	private static final String MODELWEEKFORM = "weekForm";

	private static final String MODELSCHEDULE = "schedule";

	@PostMapping("/admin/calendar")
	public ModelAndView calendar(WeekForm week) {
		ModelAndView model = new ModelAndView("admin/calendar/calendar");
		ScheduleForm schedule = new ScheduleForm();
		String sweek = week.getWeek();
		List<Schedule> lscheduleform = calendarService.getSchedule(sweek);
		model.addObject(ConstantsViews.EMPLOYEES, employeeService.allEmployeesActives());
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

	@GetMapping("/admin/newcalendar")
	public ModelAndView newcalendar() {
		ModelAndView model = new ModelAndView("admin/calendar/searchweek");
		model.addObject(MODELWEEKFORM, new WeekForm());
		return model;
	}

	@PostMapping("/admin/saveschedule")
	public ModelAndView saveSchedule(ScheduleForm sform) {
		ModelAndView model = new ModelAndView("admin/success");
		model.addObject(MODELSCHEDULE, calendarService.save(sform.getSchedule()));
		return model;
	}

	@GetMapping("/admin/searchschedule")
	public ModelAndView searchschedule() {
		ModelAndView model = new ModelAndView("admin/calendar/searchschedule");
		model.addObject(MODELWEEKFORM, new WeekForm());
		return model;
	}

	@PostMapping("/admin/resultschedule")
	public ModelAndView resultschedule(WeekForm week, BindingResult result) {
		ModelAndView model = new ModelAndView();
		String sweek = week.getWeek();
		List<Schedule> schedule = calendarService.getSchedule(sweek);
		if (schedule == null || schedule.isEmpty()) {
			model.setViewName("admin/calendar/searchschedule");
			model.addObject(MODELWEEKFORM, week);
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
			model.addObject(MODELSCHEDULE, schedule);
			model.addObject("week", sweek);
			model.setViewName("admin/calendar/schedule");
		}
		return model;
	}
}
