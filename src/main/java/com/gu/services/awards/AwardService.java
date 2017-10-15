package com.gu.services.awards;

import java.util.List;

import com.gu.dbaccess.entities.AwardEntity;
import com.gu.dbaccess.entities.AwardsChangeMachineEntity;

public interface AwardService {

	public List<AwardEntity> searchAllAwardsActive();

	public List<AwardEntity> searchAllAwardsActiveByOrder();

	public Iterable<AwardsChangeMachineEntity> getAwardsChangeMachine();

}
