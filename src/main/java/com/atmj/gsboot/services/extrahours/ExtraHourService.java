package com.atmj.gsboot.services.extrahours;

import com.atmj.gsboot.dbaccess.entities.ExtraHoursEntity;
import com.atmj.gsboot.employee.forms.ExtraHours;

public interface ExtraHourService {

	void save(ExtraHours extrahours);

	Iterable<ExtraHoursEntity> findAll();

}
