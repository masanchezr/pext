package com.gu.services.register;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.EmployeeScheduleEntity;
import com.gu.dbaccess.entities.FicticionalRegisterEntity;
import com.gu.dbaccess.entities.ScheduleEntity;
import com.gu.dbaccess.entities.TimeEntity;
import com.gu.dbaccess.repositories.FicticionalRegisterRepository;
import com.gu.dbaccess.repositories.ScheduleRepository;
import com.gu.util.constants.Constants;
import com.gu.util.date.DateUtil;

public class FicticionalRegisterServiceImpl implements FicticionalRegisterService {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private FicticionalRegisterRepository registerRepository;

	@Override
	public List<FicticionalRegisterEntity> findByDates(String datefrom, String dateuntil) {
		Date dfrom = DateUtil.getDate(datefrom);
		Date duntil = DateUtil.getDate(dateuntil);
		List<ScheduleEntity> schedule = scheduleRepository.findByDatescheduleBetween(dfrom, duntil);
		if (schedule != null && !schedule.isEmpty()) {
			Iterator<ScheduleEntity> ischedule = schedule.iterator();
			ScheduleEntity se;
			EmployeeEntity employee;
			Calendar e = Calendar.getInstance();
			Calendar d = Calendar.getInstance();
			while (ischedule.hasNext()) {
				se = ischedule.next();
				Iterator<EmployeeScheduleEntity> iemployees = se.getEmployees().iterator();
				while (iemployees.hasNext()) {
					employee = iemployees.next().getEmployee();
					saveRegister(se, employee, e, d);
				}
			}
		}
		return registerRepository.findByCreationdateBetween(dfrom, duntil);
	}

	private void saveRegister(ScheduleEntity se, EmployeeEntity employee, Calendar e, Calendar d) {
		if (!employee.getIdemployee().equals(Constants.NOBODY)) {
			FicticionalRegisterEntity fre = registerRepository.findByCreationdateAndEmployee(se.getDateschedule(),
					employee);
			if (fre == null) {
				Random rand = new Random();
				fre = new FicticionalRegisterEntity();
				TimeEntity time = se.getTime();
				fre.setCreationdate(se.getDateschedule());
				fre.setEmployee(employee);
				e.setTime(time.getEntry());
				e.set(Calendar.MINUTE, rand.nextInt(60));
				e.set(Calendar.SECOND, rand.nextInt(60));
				d.setTime(time.getDeparture());
				d.set(Calendar.MINUTE, rand.nextInt(60));
				d.set(Calendar.SECOND, rand.nextInt(60));
				fre.setTimein(e.getTime());
				fre.setTimeout(d.getTime());
				registerRepository.save(fre);
			}
		}
	}
}