package com.atmj.gsboot.services.machines;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.FunctionalityEntity;
import com.atmj.gsboot.dbaccess.entities.FunctionalityMachine;
import com.atmj.gsboot.dbaccess.entities.MachineEntity;
import com.atmj.gsboot.dbaccess.repositories.FunctionalitiesMachineRepository;
import com.atmj.gsboot.dbaccess.repositories.MachinesRepository;

@Service
public class MachineServiceImpl implements MachineService {

	@Autowired
	private MachinesRepository machinesRepository;

	@Autowired
	private FunctionalitiesMachineRepository functionalitiesMachineRepository;

	public List<MachineEntity> searchAllMachinesOrder() {
		return machinesRepository.findByOrderByOrdermachine();
	}

	public List<MachineEntity> searchMachinesOrder() {
		return machinesRepository.findByOnoffTrueOrderByOrdermachine();
	}

	public List<MachineEntity> searchMachinesByFuncionality(FunctionalityEntity functionality) {
		List<FunctionalityMachine> fml = functionalitiesMachineRepository.findByFunctionality(functionality);
		List<MachineEntity> lm = new ArrayList<>();
		Iterator<FunctionalityMachine> ifml = fml.iterator();
		while (ifml.hasNext()) {
			lm.add(ifml.next().getMachine());
		}
		return lm;
	}
}
