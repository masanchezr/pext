package com.sboot.golden.services.openmachines;

import java.util.Date;
import java.util.List;

import com.sboot.golden.dbaccess.entities.CauseEntity;
import com.sboot.golden.dbaccess.entities.OpenMachineEntity;

public interface OpenMachineService {

	public Iterable<CauseEntity> getCauses();

	public List<OpenMachineEntity> getOpenMachines(Date date);

	public void save(OpenMachineEntity om, String user);

}
