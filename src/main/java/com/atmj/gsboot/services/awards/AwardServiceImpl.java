package com.atmj.gsboot.services.awards;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.AwardEntity;
import com.atmj.gsboot.dbaccess.entities.AwardsChangeMachineEntity;
import com.atmj.gsboot.dbaccess.repositories.AwardsChangeMachineRepository;
import com.atmj.gsboot.dbaccess.repositories.AwardsRepository;

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
