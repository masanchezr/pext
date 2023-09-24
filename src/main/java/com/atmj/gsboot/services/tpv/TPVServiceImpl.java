package com.atmj.gsboot.services.tpv;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.TPVEntity;
import com.atmj.gsboot.dbaccess.repositories.TPVRepository;
import com.atmj.gsboot.services.dailies.Daily;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.util.date.DateUtil;

@Service
public class TPVServiceImpl implements TPVService {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private TPVRepository tpvrepository;

	public Daily save(TPVEntity tpv) {
		tpvrepository.save(tpv);
		return dailyService.getDaily(DateUtil.getDateFormatddMMyyyy(tpv.getCreationdate()));
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
