package com.gu.services.awards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.AwardEntity;
import com.gu.dbaccess.entities.AwardsChangeMachineEntity;
import com.gu.dbaccess.repositories.AwardsChangeMachineRepository;
import com.gu.dbaccess.repositories.AwardsRepository;

public class AwardServiceImpl implements AwardService {

	@Autowired
	private AwardsRepository awardsRepository;

	@Autowired
	private AwardsChangeMachineRepository awardschangemachinerepository;

	public List<AwardEntity> searchAllAwardsActive() {
		return awardsRepository.findByActive(true);
	}

	public List<AwardEntity> searchAllAwardsActiveByOrder() {
		return awardsRepository.findByActiveOrderByOrder(true);
	}

	public Iterable<AwardsChangeMachineEntity> getAwardsChangeMachine() {
		return awardschangemachinerepository.findAll();
	}
}
