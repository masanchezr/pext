package com.atmj.gsboot.services.extrahours;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.ExtraHoursEntity;
import com.atmj.gsboot.dbaccess.repositories.ExtraHoursRepository;
import com.atmj.gsboot.employee.forms.ExtraHours;

@Service
public class ExtraHourServiceImpl implements ExtraHourService {

	@Autowired
	private ExtraHoursRepository extrahoursRepository;

	@Autowired
	private ModelMapper mapper;

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
