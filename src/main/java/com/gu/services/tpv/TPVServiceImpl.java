package com.gu.services.tpv;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.TPVEntity;
import com.gu.dbaccess.repositories.TPVRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;

public class TPVServiceImpl implements TPVService {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private TPVRepository tpvrepository;

	public Daily save(TPVEntity tpv) {
		tpvrepository.save(tpv);
		return dailyService.getDaily(new Date());
	}

	@Override
	public boolean exists(Long idtpv) {
		return tpvrepository.existsById(idtpv);
	}

	public List<TPVEntity> getOperationsTpv(Date from, Date until) {
		return tpvrepository.findByCreationdateBetween(from, until);
	}

	public BigDecimal sumByCreationdate(Date from, Date until) {
		return tpvrepository.sumByCreationdate(from, until);
	}

}
