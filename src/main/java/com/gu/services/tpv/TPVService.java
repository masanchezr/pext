package com.gu.services.tpv;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gu.dbaccess.entities.TPVEntity;
import com.gu.services.dailies.Daily;

public interface TPVService {

	public Daily save(TPVEntity tpv);

	public List<TPVEntity> getOperationsTpv(Date from, Date until);

	public BigDecimal sumByCreationdate(Date from, Date until);

	public boolean exists(Long idtpv);

}
