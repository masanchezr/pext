package com.gu.services.tpv;

import java.util.Map;

import com.gu.dbaccess.entities.TPVEntity;
import com.gu.services.dailies.Daily;

public interface TPVService {

	public Daily save(TPVEntity tpv);

	public TPVEntity findOne(TPVEntity tpv);

	public Map<String, ?> getOperationsTpv(String datefrom);

}
