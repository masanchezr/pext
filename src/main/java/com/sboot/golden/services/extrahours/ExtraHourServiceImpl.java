package com.sboot.golden.services.extrahours;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.ExtraHoursEntity;
import com.sboot.golden.dbaccess.repositories.ExtraHoursRepository;
import com.sboot.golden.employee.forms.ExtraHours;

@Service
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
