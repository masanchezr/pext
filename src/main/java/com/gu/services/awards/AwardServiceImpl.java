package com.gu.services.awards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.AwardEntity;
import com.gu.dbaccess.repositories.AwardsRepository;

public class AwardServiceImpl implements AwardService {

	@Autowired
	private AwardsRepository awardsRepository;

	public List<AwardEntity> searchAllAwardsActive() {
		return awardsRepository.findByActive(true);
	}

	public List<AwardEntity> searchAllAwardsActiveByOrder() {
		return awardsRepository.findByActiveOrderByOrder(true);
	}

}
