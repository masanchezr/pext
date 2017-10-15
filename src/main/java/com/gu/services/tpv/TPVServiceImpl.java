package com.gu.services.tpv;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.TPVEntity;
import com.gu.dbaccess.repositories.TPVRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;

public class TPVServiceImpl implements TPVService {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private TPVRepository tpvrepository;

	public Daily save(TPVEntity tpv) {
		Date today = new Date();
		tpv.setCreationdate(today);
		tpvrepository.save(tpv);
		return dailyService.getDailyEmployee(today);
	}

	public TPVEntity findOne(TPVEntity tpv) {
		return tpvrepository.findOne(tpv.getIdtpv());
	}

	public Map<String, ?> getOperationsTpv(String month) {
		Map<String, Object> map = null;
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		List<TPVEntity> operations = tpvrepository.findByCreationdateBetween(from, until);
		if (operations != null) {
			map = new HashMap<String, Object>();
			map.put("operations", operations);
			map.put("amount", tpvrepository.sumByCreationdate(from, until));
		}
		return map;
	}

}
