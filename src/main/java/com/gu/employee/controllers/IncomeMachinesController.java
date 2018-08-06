package com.gu.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.FunctionalityEntity;
import com.gu.dbaccess.entities.IncomeMachineEntity;
import com.gu.services.incomemachines.IncomeMachineService;
import com.gu.services.machines.MachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class IncomeMachinesController {

	@Autowired
	private IncomeMachineService incomemachineService;

	@Autowired
	private MachineService machineservice;

	@RequestMapping(value = "/employee/newincomemachine")
	public ModelAndView newincomemachine() {
		ModelAndView model = new ModelAndView("incomemachine");
		FunctionalityEntity functionality = new FunctionalityEntity();
		functionality.setIdfuncionality(Constants.INCOMEMACHINE);
		model.addObject(Constants.MACHINES, machineservice.searchMachinesByFuncionality(functionality));
		model.addObject(ConstantsJsp.FORMINCOME, new IncomeMachineEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincomemachine")
	public ModelAndView saveincomemachine(@ModelAttribute(ConstantsJsp.FORMINCOME) IncomeMachineEntity imachine) {
		ModelAndView model = new ModelAndView(ConstantsJsp.VIEWSUCCESSEMPLOYEE);
		model.addObject(ConstantsJsp.DAILY, incomemachineService.save(imachine));
		model.setViewName(ConstantsJsp.DAILY);
		model.addObject(ConstantsJsp.DATEDAILY, new Date());
		return model;
	}

}
