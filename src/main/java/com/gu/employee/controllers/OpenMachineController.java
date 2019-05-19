package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.FunctionalityEntity;
import com.gu.dbaccess.entities.OpenMachineEntity;
import com.gu.services.machines.MachineService;
import com.gu.services.openmachines.OpenMachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class OpenMachineController {

	@Autowired
	private OpenMachineService openmachineservice;

	@Autowired
	private MachineService machineservice;

	@RequestMapping("/employee/newopenmachine")
	public ModelAndView newopenmachine() {
		ModelAndView model = new ModelAndView("newopenmachine");
		FunctionalityEntity functionality = new FunctionalityEntity();
		functionality.setIdfuncionality(Constants.OPENMACHINE);
		model.addObject("causes", openmachineservice.getCauses());
		model.addObject(ConstantsJsp.FORMOPENMACHINE, new OpenMachineEntity());
		model.addObject(Constants.MACHINES, machineservice.searchMachinesByFuncionality(functionality));
		return model;
	}

	@RequestMapping("/employee/saveopenmachine")
	public ModelAndView saveopenmachine(@ModelAttribute(ConstantsJsp.FORMOPENMACHINE) OpenMachineEntity om) {
		ModelAndView model = new ModelAndView(Constants.OPENMACHINES);
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		openmachineservice.save(om, user);
		model.addObject(Constants.OPENMACHINES, openmachineservice.getOpenMachines(om.getCreationdate()));
		return model;
	}

}
