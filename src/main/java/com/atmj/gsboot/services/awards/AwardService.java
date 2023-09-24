package com.atmj.gsboot.services.awards;

import java.util.List;

import com.atmj.gsboot.dbaccess.entities.AwardEntity;
import com.atmj.gsboot.dbaccess.entities.AwardsChangeMachineEntity;

public interface AwardService {

	public List<AwardEntity> searchAllAwardsActive();

	public List<AwardEntity> searchAllAwardsActiveByOrder();

	public Iterable<AwardsChangeMachineEntity> getAwardsChangeMachine();

}
