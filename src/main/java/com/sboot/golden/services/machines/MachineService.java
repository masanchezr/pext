package com.sboot.golden.services.machines;

import java.util.List;

import com.sboot.golden.dbaccess.entities.FunctionalityEntity;
import com.sboot.golden.dbaccess.entities.MachineEntity;

public interface MachineService {

	public List<MachineEntity> searchAllMachinesOrder();

	public List<MachineEntity> searchMachinesOrder();

	public List<MachineEntity> searchMachinesByFuncionality(FunctionalityEntity functionality);

}
