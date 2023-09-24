package com.atmj.gsboot.services.machines;

import java.util.List;

import com.atmj.gsboot.dbaccess.entities.FunctionalityEntity;
import com.atmj.gsboot.dbaccess.entities.MachineEntity;

public interface MachineService {

	public List<MachineEntity> searchAllMachinesOrder();

	public List<MachineEntity> searchMachinesOrder();

	public List<MachineEntity> searchMachinesByFuncionality(FunctionalityEntity functionality);

}
