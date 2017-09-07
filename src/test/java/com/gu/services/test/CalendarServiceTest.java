package com.gu.services.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.admin.forms.Schedule;
import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.services.calendar.CalendarService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class CalendarServiceTest {

	@Autowired
	private CalendarService calendarservice;

	@Test
	public void saveTest() {
		List<Schedule> schedule = new ArrayList<Schedule>();
		Schedule sone = new Schedule();
		Schedule stwo = new Schedule();
		TimeEntity time = new TimeEntity();
		List<EmployeeEntity> employees = new ArrayList<EmployeeEntity>();
		EmployeeEntity one = new EmployeeEntity();
		EmployeeEntity two = new EmployeeEntity();
		one.setIdemployee(2L);
		two.setIdemployee(4L);
		employees.add(one);
		employees.add(two);
		time.setIdtime(7L);
		sone.setDateschedule("2017-03-06");
		sone.setTime(time);
		sone.setEmployees(employees);
		time.setIdtime(1L);
		stwo.setDateschedule("2017-03-06");
		stwo.setTime(time);
		stwo.setEmployees(employees);
		schedule.add(sone);
		schedule.add(stwo);
		// calendarservice.save(schedule);

	}

}
