package com.gu.employee.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.FunctionalityEntity;
import com.gu.dbaccess.entities.IncomeMachineEntity;
import com.gu.employee.forms.IncomeMachine;
import com.gu.services.dailies.DailyService;
import com.gu.services.incomemachines.IncomeMachineService;
import com.gu.services.machines.MachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;

@Controller
public class IncomeMachinesController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeMachineService incomemachineService;

	@Autowired
	private MachineService machineservice;

	@Autowired
	private Mapper mapper;

	@GetMapping("/employee/newincomemachine")
	public ModelAndView newincomemachine() {
		ModelAndView model = new ModelAndView("employee/income/incomemachine");
		FunctionalityEntity functionality = new FunctionalityEntity();
		functionality.setIdfuncionality(Constants.INCOMEMACHINE);
		model.addObject(Constants.MACHINES, machineservice.searchMachinesByFuncionality(functionality));
		model.addObject(ConstantsJsp.FORMINCOME, new IncomeMachine());
		return model;
	}

	@PostMapping("/employee/saveincomemachine")
	public ModelAndView saveincomemachine(@ModelAttribute(ConstantsJsp.FORMINCOME) IncomeMachine imachine) {
		ModelAndView model = new ModelAndView();
		incomemachineService.save(mapper.map(imachine, IncomeMachineEntity.class));
		model.addObject(ConstantsJsp.DAILY, dailyService.getDailyEmployee());
		model.setViewName("employee/daily/daily");
		model.addObject(ConstantsJsp.DATEDAILY, new DateUtil().getNow());
		return model;
	}

}
