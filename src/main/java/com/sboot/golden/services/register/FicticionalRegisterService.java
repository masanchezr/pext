package com.sboot.golden.services.register;

import java.io.File;
import java.util.List;

import com.sboot.golden.dbaccess.entities.FicticionalRegisterEntity;

public interface FicticionalRegisterService {

	public List<FicticionalRegisterEntity> findByDates(String datefrom, String dateuntil);

	public void generatePdf(List<FicticionalRegisterEntity> register, File file);

}
