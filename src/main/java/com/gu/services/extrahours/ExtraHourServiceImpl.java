package com.gu.services.extrahours;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.ExtraHoursEntity;
import com.gu.dbaccess.repositories.ExtraHoursRepository;
import com.gu.employee.forms.ExtraHours;

public class ExtraHourServiceImpl implements ExtraHourService {

	@Autowired
	private ExtraHoursRepository extrahoursRepository;

	@Override
	public void save(ExtraHours extrahours) {
		ExtraHoursEntity entity = new ExtraHoursEntity();
		Calendar c = Calendar.getInstance();
		entity.setEmployee(extrahours.getEmployee());
		entity.setDescription(extrahours.getDescription());
		entity.setSystemtime(new Date());
		extrahoursRepository.save(entity);

	}

}
