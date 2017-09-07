package com.gu.services.awards;

import java.util.List;

import com.gu.dbaccess.entities.AwardEntity;

public interface AwardService {

	public List<AwardEntity> searchAllAwardsActive();

	public List<AwardEntity> searchAllAwardsActiveByOrder();

}
