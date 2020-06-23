package com.gu.services.extrahours;

import com.gu.dbaccess.entities.ExtraHoursEntity;
import com.gu.employee.forms.ExtraHours;

public interface ExtraHourService {

	void save(ExtraHours extrahours);

	Iterable<ExtraHoursEntity> findAll();

}
