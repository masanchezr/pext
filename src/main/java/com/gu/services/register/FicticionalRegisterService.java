package com.gu.services.register;

import java.util.List;

import com.gu.dbaccess.entities.FicticionalRegisterEntity;

public interface FicticionalRegisterService {

	public List<FicticionalRegisterEntity> findByDates(String datefrom, String dateuntil);

}
