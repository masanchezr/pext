package com.atmj.gsboot.services.calendar;

import java.util.List;

import com.atmj.gsboot.admin.forms.Schedule;
import com.atmj.gsboot.dbaccess.entities.ScheduleEntity;
import com.atmj.gsboot.dbaccess.entities.TimeEntity;

public interface CalendarService {

	public List<TimeEntity> getTimesActive();

	public List<ScheduleEntity> save(List<Schedule> list);

	public List<Schedule> getSchedule(String sweek);

	public Iterable<TimeEntity> getTimes();

}
