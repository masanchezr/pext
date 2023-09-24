package com.atmj.gsboot.services.openmachines;

import java.util.Date;
import java.util.List;

import com.atmj.gsboot.dbaccess.entities.CauseEntity;
import com.atmj.gsboot.dbaccess.entities.OpenMachineEntity;

public interface OpenMachineService {

	public Iterable<CauseEntity> getCauses();

	public List<OpenMachineEntity> getOpenMachines(Date date);

	public void save(OpenMachineEntity om, String user);

}
