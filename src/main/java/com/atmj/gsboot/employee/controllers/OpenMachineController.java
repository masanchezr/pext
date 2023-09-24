package com.atmj.gsboot.employee.controllers;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.FunctionalityEntity;
import com.atmj.gsboot.dbaccess.entities.OpenMachineEntity;
import com.atmj.gsboot.employee.forms.OpenMachine;
import com.atmj.gsboot.services.machines.MachineService;
import com.atmj.gsboot.services.openmachines.OpenMachineService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

@Controller
public class OpenMachineController {

	@Autowired
	private MachineService machineservice;

	@Autowired
	private OpenMachineService openmachineservice;

	@Autowired
	private ModelMapper mapper;

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
