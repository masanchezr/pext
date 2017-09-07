package com.gu.services.gratifications;

import java.util.List;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.services.dailies.Daily;

public interface GratificationService {

	public Daily save(GratificationEntity g);

	public boolean exists(Long idgratification);

	public List<GratificationEntity> lastNumGratifications();

	public void registerNumberGratification(GratificationEntity g);

	public GratificationEntity searchGratificationActive(Long idgratification);

}
