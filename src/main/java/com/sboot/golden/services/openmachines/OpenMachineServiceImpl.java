package com.sboot.golden.services.openmachines;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.CauseEntity;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.entities.OpenMachineEntity;
import com.sboot.golden.dbaccess.repositories.CausesRepository;
import com.sboot.golden.dbaccess.repositories.UsersRepository;
import com.sboot.golden.dbaccess.repositories.OpenMachinesRepository;
import com.sboot.golden.util.date.DateUtil;

@Service
public class OpenMachineServiceImpl implements OpenMachineService {

	@Autowired
	private CausesRepository causesrepository;

	@Autowired
	private UsersRepository employeesrepository;

	@Autowired
	private OpenMachinesRepository openmachinesrepository;

	@Override
	public Iterable<CauseEntity> getCauses() {
		return causesrepository.findAll();
	}

	@Override
	public List<OpenMachineEntity> getOpenMachines(Date date) {
		return openmachinesrepository.findByCreationdate(date);
	}

	@Override
	public void save(OpenMachineEntity om, String user) {
		UserEntity employee = employeesrepository.findByUsername(user);
		om.setEmployee(employee);
		om.setCreationdate(new DateUtil().getNow());
		openmachinesrepository.save(om);
	}

}
