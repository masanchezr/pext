package com.gu.services.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.admin.forms.Schedule;
import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ScheduleEntity;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.dbaccess.repositories.ScheduleRepository;
import com.gu.dbaccess.repositories.TimeRepository;
import com.gu.util.constants.Constants;
import com.gu.util.date.DateUtil;

public class CalendarServiceImpl implements CalendarService {

	@Autowired
	private TimeRepository timeRepository;

	@Autowired
	private ScheduleRepository scheduleRepository;

	public List<TimeEntity> getTimesActive() {
		return timeRepository.findByActiveTrueOrderByOrder();
	}

	public List<ScheduleEntity> save(List<Schedule> schedule) {
		List<ScheduleEntity> calendar = new ArrayList<ScheduleEntity>(), lschedule;
		Iterator<Schedule> ischedule = schedule.iterator();
		ScheduleEntity s;
		while (ischedule.hasNext()) {
			lschedule = mapper(ischedule.next());
			for (int i = 0; i < lschedule.size(); i++) {
				s = lschedule.get(i);
				calendar.add(scheduleRepository.save(s));
			}
		}
		return calendar;
	}

	private List<ScheduleEntity> mapper(Schedule next) {
		List<ScheduleEntity> calendar = new ArrayList<ScheduleEntity>();
		List<EmployeeEntity> employees = next.getEmployees();
		for (EmployeeEntity employee : employees) {
			ScheduleEntity s = new ScheduleEntity();
			s.setDateschedule(DateUtil.getDate(next.getDateschedule()));
			s.setTime(next.getTime());
			if ((employee.getIdemployee().equals(Constants.NOBODY)
					|| employee.getIdemployee().equals(Constants.EVERYBODY)) && employees.size() == 1) {
				s.setEmployee(employee);
				calendar.add(s);
			} else if (!employee.getIdemployee().equals(Constants.NOBODY)
					&& !employee.getIdemployee().equals(Constants.EVERYBODY) && employees.size() > 1) {
				s.setEmployee(employee);
				calendar.add(s);
			}
		}
		if (calendar.isEmpty()) {
			ScheduleEntity s = new ScheduleEntity();
			s.setDateschedule(DateUtil.getDate(next.getDateschedule()));
			s.setTime(next.getTime());
			s.setEmployee(employees.get(0));
			calendar.add(s);
		}
		return calendar;
	}

	public List<Schedule> getSchedule(String week) {
		List<Date> dates = DateUtil.getDates(week);
		List<ScheduleEntity> lschedule = scheduleRepository.findByDatescheduleBetween(dates.get(0),
				dates.get(dates.size() - 1));
		List<Schedule> lscheduleform = new ArrayList<Schedule>();
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		ScheduleEntity schedule, scheduletwo;
		Schedule scheduleform = new Schedule();
		for (int i = 0; i < lschedule.size(); i++) {
			schedule = lschedule.get(i);
			scheduleform.setDateschedule(DateUtil.getStringDateFormatdd_MM_yyyy(schedule.getDateschedule()));
			scheduleform.setTime(schedule.getTime());
			scheduleform.setEmployees(employees);
			if (i < lschedule.size() - 1) {
				if (!scheduleform.getEmployees().contains(schedule.getEmployee())) {
					scheduleform.getEmployees().add(schedule.getEmployee());
				}
				scheduletwo = lschedule.get(i + 1);
				if (schedule.getTime().equals(scheduletwo.getTime())
						&& schedule.getDateschedule().equals(scheduletwo.getDateschedule())
						&& !schedule.getEmployee().equals(scheduletwo.getEmployee())
						&& !scheduleform.getEmployees().contains(scheduletwo.getEmployee())) {
					scheduleform.getEmployees().add(scheduletwo.getEmployee());
				} else {
					lscheduleform.add(scheduleform);
					scheduleform = new Schedule();
					employees = new ArrayList<EmployeeEntity>();
				}
			} else {
				scheduleform.getEmployees().add(schedule.getEmployee());
				lscheduleform.add(scheduleform);
			}
		}
		return lscheduleform;
	}

	public Iterable<TimeEntity> getTimes() {
		return timeRepository.findByOrderByOrder();
	}

}
