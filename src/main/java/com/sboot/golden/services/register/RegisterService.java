package com.sboot.golden.services.register;

import java.io.File;
import java.util.List;

import com.sboot.golden.dbaccess.entities.RegisterEntity;

public interface RegisterService {

	public void registerIn(String user, String ipaddress);

	public void registerOut(String user, String ipaddress);

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil);

	public void generatePdf(List<RegisterEntity> register, File file);

}
