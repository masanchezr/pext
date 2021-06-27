package com.sboot.golden.services.awards;

import java.util.List;

import com.sboot.golden.dbaccess.entities.AwardEntity;
import com.sboot.golden.dbaccess.entities.AwardsChangeMachineEntity;

public interface AwardService {

	public List<AwardEntity> searchAllAwardsActive();

	public List<AwardEntity> searchAllAwardsActiveByOrder();

	public Iterable<AwardsChangeMachineEntity> getAwardsChangeMachine();

}
