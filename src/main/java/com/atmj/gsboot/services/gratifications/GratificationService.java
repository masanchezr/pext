package com.atmj.gsboot.services.gratifications;

import java.util.List;

import com.atmj.gsboot.dbaccess.entities.GratificationEntity;
import com.atmj.gsboot.services.dailies.Daily;

public interface GratificationService {

	public Daily save(GratificationEntity g, String user);

	public List<GratificationEntity> lastNumGratifications();

	public void registerNumberGratification(GratificationEntity g, String user, String path);

	public GratificationEntity searchGratificationActive(Long idgratification);

}
