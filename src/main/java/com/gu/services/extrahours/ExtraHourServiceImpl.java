package com.gu.services.extrahours;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.ExtraHoursEntity;
import com.gu.dbaccess.repositories.ExtraHoursRepository;
import com.gu.employee.forms.ExtraHours;

public class ExtraHourServiceImpl implements ExtraHourService {

	@Autowired
	private ExtraHoursRepository extrahoursRepository;

	@Autowired
	private Mapper mapper;

	@Override
	public void save(ExtraHours extrahours) {
		ExtraHoursEntity entity = mapper.map(extrahours, ExtraHoursEntity.class);
		entity.setSystemtime(new Date());
		extrahoursRepository.save(entity);
	}

	@Override
	public Iterable<ExtraHoursEntity> findAll() {
		return extrahoursRepository.findAll();
	}

}
