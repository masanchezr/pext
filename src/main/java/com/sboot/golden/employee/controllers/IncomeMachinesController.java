package com.sboot.golden.employee.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.FunctionalityEntity;
import com.sboot.golden.dbaccess.entities.IncomeMachineEntity;
import com.sboot.golden.employee.forms.IncomeMachine;
import com.sboot.golden.services.dailies.DailyService;
import com.sboot.golden.services.incomemachines.IncomeMachineService;
import com.sboot.golden.services.machines.MachineService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

@Controller
public class IncomeMachinesController {

	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeMachineService incomemachineService;

	@Autowired
	private MachineService machineservice;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/employee/newincomemachine")
	public ModelAndView newincomemachine() {
		ModelAndView model = new ModelAndView("employee/income/incomemachine");
		FunctionalityEntity functionality = new FunctionalityEntity();
		functionality.setIdfuncionality(Constants.INCOMEMACHINE);
		model.addObject(Constants.MACHINES, machineservice.searchMachinesByFuncionality(functionality));
		model.addObject(ConstantsViews.FORMINCOME, new IncomeMachine());
		return model;
	}

	@PostMapping("/employee/saveincomemachine")
	public ModelAndView saveincomemachine(@ModelAttribute(ConstantsViews.FORMINCOME) IncomeMachine imachine) {
		ModelAndView model = new ModelAndView();
		incomemachineService.save(mapper.map(imachine, IncomeMachineEntity.class));
		model.addObject(ConstantsViews.DAILY, dailyService.getDailyEmployee());
		model.setViewName("employee/daily/daily");
		model.addObject(ConstantsViews.DATEDAILY, new DateUtil().getNow());
		return model;
	}

}
