package com.gu.services.gratifications;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.dbaccess.repositories.GratificationsRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.date.DateUtil;

public class GratificationServiceImpl implements GratificationService {

	@Autowired
	private GratificationsRepository gratificationRepository;

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	public Daily save(GratificationEntity g) {
		g.setPaydate(new Date());
		gratificationRepository.save(g);
		return dailyService.getDailyEmployee(new Date());
	}

	public boolean exists(Long id) {
		GratificationEntity gratification = gratificationRepository.findOne(id);
		if (gratification == null) {
			return false;
		} else if (gratification.getPaydate() == null) {
			return true;
		} else {
			return false;
		}
	}

	public GratificationEntity searchGratificationActive(Long id) {
		GratificationEntity g = gratificationRepository.findByIdgratificationAndPaydateIsNull(id);
		if (g != null) {
			Calendar c = Calendar.getInstance();
			Calendar t = Calendar.getInstance();
			c.setTime(g.getCreationdate());
			t.setTime(new Date());
			if (t.get(Calendar.DAY_OF_MONTH) - c.get(Calendar.DAY_OF_MONTH) == 1) {
				return g;
			} else {
				return null;
			}
		} else {
			return null;

		}
	}

	public List<GratificationEntity> lastNumGratifications() {
		return gratificationRepository.findByCreationdateBetweenAndPaydateIsNull(DateUtil.addDays(new Date(), -1),
				new Date());
	}

	public void registerNumberGratification(GratificationEntity g) {
		g.setCreationdate(new Date());
		gratificationRepository.save(g);
	}
}
