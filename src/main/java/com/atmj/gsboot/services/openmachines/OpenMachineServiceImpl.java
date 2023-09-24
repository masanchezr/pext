package com.atmj.gsboot.services.openmachines;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.CauseEntity;
import com.atmj.gsboot.dbaccess.entities.UserEntity;
import com.atmj.gsboot.dbaccess.entities.OpenMachineEntity;
import com.atmj.gsboot.dbaccess.repositories.CausesRepository;
import com.atmj.gsboot.dbaccess.repositories.UsersRepository;
import com.atmj.gsboot.dbaccess.repositories.OpenMachinesRepository;
import com.atmj.gsboot.util.date.DateUtil;

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
