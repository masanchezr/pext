package com.gu.services.gratifications;

import java.util.List;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.services.dailies.Daily;

public interface GratificationService {

	public Daily save(GratificationEntity g, String user);

	public List<GratificationEntity> lastNumGratifications();

	public void registerNumberGratification(GratificationEntity g, String user, String path);

	public GratificationEntity searchGratificationActive(Long idgratification);

}
