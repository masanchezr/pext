package com.gu.services.register;

import java.io.File;
import java.util.List;

import com.gu.dbaccess.entities.FicticionalRegisterEntity;

public interface FicticionalRegisterService {

	public List<FicticionalRegisterEntity> findByDates(String datefrom, String dateuntil);

	public void generatePdf(List<FicticionalRegisterEntity> register, File file);

}
