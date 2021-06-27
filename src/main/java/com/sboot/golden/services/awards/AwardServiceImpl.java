package com.sboot.golden.services.awards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.AwardEntity;
import com.sboot.golden.dbaccess.entities.AwardsChangeMachineEntity;
import com.sboot.golden.dbaccess.repositories.AwardsChangeMachineRepository;
import com.sboot.golden.dbaccess.repositories.AwardsRepository;

@Service
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
