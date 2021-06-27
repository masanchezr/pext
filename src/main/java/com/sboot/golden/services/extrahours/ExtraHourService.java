package com.sboot.golden.services.extrahours;

import com.sboot.golden.dbaccess.entities.ExtraHoursEntity;
import com.sboot.golden.employee.forms.ExtraHours;

public interface ExtraHourService {

	void save(ExtraHours extrahours);

	Iterable<ExtraHoursEntity> findAll();

}
