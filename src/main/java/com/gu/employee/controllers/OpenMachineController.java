package com.gu.employee.controllers;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.FunctionalityEntity;
import com.gu.dbaccess.entities.OpenMachineEntity;
import com.gu.employee.forms.OpenMachine;
import com.gu.services.machines.MachineService;
import com.gu.services.openmachines.OpenMachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsViews;
import com.gu.util.date.DateUtil;

@Controller
public class OpenMachineController {

	@Autowired
	private MachineService machineservice;

	@Autowired
	private OpenMachineService openmachineservice;

	@Autowired
	private Mapper mapper;

	@GetMapping("/employee/newopenmachine")
	public ModelAndView newopenmachine() {
		ModelAndView model = new ModelAndView("employee/openmachines/newopenmachine");
		FunctionalityEntity functionality = new FunctionalityEntity();
		functionality.setIdfuncionality(Constants.OPENMACHINE);
		model.addObject("causes", openmachineservice.getCauses());
		model.addObject(ConstantsViews.FORMOPENMACHINE, new OpenMachine());
		model.addObject(Constants.MACHINES, machineservice.searchMachinesByFuncionality(functionality));
		return model;
	}

	@PostMapping("/employee/saveopenmachine")
	public ModelAndView saveopenmachine(@ModelAttribute(ConstantsViews.FORMOPENMACHINE) OpenMachine om) {
		ModelAndView model = new ModelAndView("employee/openmachines/openmachines");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		openmachineservice.save(mapper.map(om, OpenMachineEntity.class), user);
		model.addObject(Constants.OPENMACHINES,
				openmachineservice.getOpenMachines(DateUtil.getDateFormatddMMyyyy(new Date())));
		return model;
	}

}
