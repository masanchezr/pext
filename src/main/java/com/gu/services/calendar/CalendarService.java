package com.gu.services.calendar;

import java.util.List;

import com.gu.admin.forms.Schedule;
import com.gu.dbaccess.entities.ScheduleEntity;
import com.gu.dbaccess.entities.TimeEntity;

public interface CalendarService {

	public List<TimeEntity> getTimesActive();

	public List<ScheduleEntity> save(List<Schedule> list);

	public List<Schedule> getSchedule(String sweek);

	public Iterable<TimeEntity> getTimes();

}
