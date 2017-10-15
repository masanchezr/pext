package com.gu.services.machines;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.MachineEntity;
import com.gu.dbaccess.repositories.MachinesRepository;

public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachinesRepository machinesRepository;

	public List<MachineEntity> searchAllMachinesOrder() {
		return machinesRepository.findByOrderByOrdermachine();
	}

	public List<MachineEntity> searchMachinesOrder() {
		return machinesRepository.findByOnoffTrueOrderByOrdermachine();
	}

}
