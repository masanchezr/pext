package com.gu.services.machines;

import java.util.List;

import com.gu.dbaccess.entities.FunctionalityEntity;
import com.gu.dbaccess.entities.MachineEntity;

public interface MachineService {

	public List<MachineEntity> searchAllMachinesOrder();

	public List<MachineEntity> searchMachinesOrder();

	public List<MachineEntity> searchMachinesByFuncionality(FunctionalityEntity functionality);

}
