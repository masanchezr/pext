package com.atmj.gsboot.services.tpv;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.atmj.gsboot.dbaccess.entities.TPVEntity;
import com.atmj.gsboot.services.dailies.Daily;

public interface TPVService {

	public Daily save(TPVEntity tpv);

	public List<TPVEntity> getOperationsTpv(Date from, Date until);

	public BigDecimal sumByCreationdate(Date from, Date until);

	public boolean exists(Long idtpv);

}
