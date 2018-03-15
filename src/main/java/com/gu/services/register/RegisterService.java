package com.gu.services.register;

import java.util.Date;
import java.util.List;

import com.gu.dbaccess.entities.RegisterEntity;

public interface RegisterService {

	public void register(String user, String ipaddress, Boolean inout);

	public List<RegisterEntity> findByDate(Date date);

	public List<RegisterEntity> findByDates(String datefrom, String dateuntil);

}
