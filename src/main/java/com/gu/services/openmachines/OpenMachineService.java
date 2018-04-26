package com.gu.services.openmachines;

import java.util.Date;
import java.util.List;

import com.gu.dbaccess.entities.CauseEntity;
import com.gu.dbaccess.entities.OpenMachineEntity;

public interface OpenMachineService {

	public Iterable<CauseEntity> getCauses();

	public List<OpenMachineEntity> getOpenMachines(Date date);

	public void save(OpenMachineEntity om, String user);

}
