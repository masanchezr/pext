package com.sboot.golden.services.calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.sboot.golden.admin.forms.Schedule;
import com.sboot.golden.dbaccess.entities.ScheduleEntity;
import com.sboot.golden.dbaccess.entities.TimeEntity;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.repositories.ScheduleRepository;
import com.sboot.golden.dbaccess.repositories.TimeRepository;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.date.DateUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
		ScheduleEntity s = new ScheduleEntity();
		s.setDateschedule(DateUtil.getDate(next.getDateschedule()));
		s.setTime(next.getTime());
		s.setEmployee(next.getEmployee());
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
		UserEntity employee;
		while (ilschedule.hasNext()) {
			schedule = new Schedule();
			entity = ilschedule.next();
			schedule.setDateschedule(DateUtil.getStringDateFormatddMMyyyy(entity.getDateschedule()));
			schedule.setTime(entity.getTime());
			employee = entity.getEmployee();
			if (!employee.getId().equals(Constants.NOBODY)) {
				schedule.setEmployee(employee);
			}
			calendar.add(schedule);
		}
		return calendar;

	}

	public Iterable<TimeEntity> getTimes() {
		return timeRepository.findByOrderByOrder();
	}

}
