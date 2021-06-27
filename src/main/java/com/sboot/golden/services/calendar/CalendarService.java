package com.sboot.golden.services.calendar;

import java.util.List;

import com.sboot.golden.admin.forms.Schedule;
import com.sboot.golden.dbaccess.entities.ScheduleEntity;
import com.sboot.golden.dbaccess.entities.TimeEntity;

public interface CalendarService {

	public List<TimeEntity> getTimesActive();

	public List<ScheduleEntity> save(List<Schedule> list);

	public List<Schedule> getSchedule(String sweek);

	public Iterable<TimeEntity> getTimes();

}
