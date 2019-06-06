package com.gu.services.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.admin.forms.Schedule;
import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.EmployeeScheduleEntity;
import com.gu.dbaccess.entities.ScheduleEntity;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.dbaccess.repositories.ScheduleRepository;
import com.gu.dbaccess.repositories.TimeRepository;
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
		List<ScheduleEntity> calendar = new ArrayList<>();
		Iterator<Schedule> ischedule = schedule.iterator();
		while (ischedule.hasNext()) {
			calendar.add(scheduleRepository.save(mapper(ischedule.next())));
		}
		return calendar;
	}

	private ScheduleEntity mapper(Schedule next) {
		List<EmployeeEntity> employees = next.getEmployees();
		ScheduleEntity s = new ScheduleEntity();
		s.setDateschedule(DateUtil.getDate(next.getDateschedule()));
		s.setTime(next.getTime());
		s.setEmployees(new ArrayList<>());
		for (EmployeeEntity employee : employees) {
			EmployeeScheduleEntity e = new EmployeeScheduleEntity();
			e.setEmployee(employee);
			e.setSchedule(s);
			s.getEmployees().add(e);
		}
		return s;
	}

	public List<Schedule> getSchedule(String week) {
		List<Date> dates = DateUtil.getDates(week);
		List<Schedule> calendar = new ArrayList<>();
		List<ScheduleEntity> lschedule = scheduleRepository.findByDatescheduleBetween(
				DateUtil.getDateFormatddMMyyyy(dates.get(0)),
				DateUtil.getDateFormatddMMyyyy(dates.get(dates.size() - 1)));
		Iterator<ScheduleEntity> ilschedule = lschedule.iterator();
		Schedule schedule;
		ScheduleEntity entity;
		List<EmployeeScheduleEntity> employees;
		Iterator<EmployeeScheduleEntity> iemployees;
		while (ilschedule.hasNext()) {
			schedule = new Schedule();
			entity = ilschedule.next();
			schedule.setDateschedule(DateUtil.getStringDateFormatddMMyyyy(entity.getDateschedule()));
			schedule.setTime(entity.getTime());
			schedule.setEmployees(new ArrayList<>());
			employees = entity.getEmployees();
			iemployees = employees.iterator();
			while (iemployees.hasNext()) {
				schedule.getEmployees().add(iemployees.next().getEmployee());
			}
			calendar.add(schedule);
		}
		return calendar;
	}

	public Iterable<TimeEntity> getTimes() {
		return timeRepository.findByOrderByOrder();
	}

}
