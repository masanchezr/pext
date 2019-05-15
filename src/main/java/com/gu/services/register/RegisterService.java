package com.gu.services.register;

import java.util.List;

import com.gu.dbaccess.entities.RegisterEntity;

public interface RegisterService {

	public void registerIn(String user, String ipaddress);

	public void registerOut(String user, String ipaddress);

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil);

}
