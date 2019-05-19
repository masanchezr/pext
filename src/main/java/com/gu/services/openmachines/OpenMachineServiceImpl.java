package com.gu.services.openmachines;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.CauseEntity;
import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.OpenMachineEntity;
import com.gu.dbaccess.repositories.CausesRepository;
import com.gu.dbaccess.repositories.EmployeesRepository;
import com.gu.dbaccess.repositories.OpenMachinesRepository;
import com.gu.util.date.DateUtil;

public class OpenMachineServiceImpl implements OpenMachineService {

	@Autowired
	private CausesRepository causesrepository;

	@Autowired
	private EmployeesRepository employeesrepository;

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
		EmployeeEntity employee = employeesrepository.findByUsername(user);
		om.setEmployee(employee);
		om.setCreationdate(new DateUtil().getNow());
		openmachinesrepository.save(om);
	}

}
