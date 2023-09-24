package com.atmj.gsboot.employee.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.FunctionalityEntity;
import com.atmj.gsboot.dbaccess.entities.IncomeMachineEntity;
import com.atmj.gsboot.employee.forms.IncomeMachine;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.services.incomemachines.IncomeMachineService;
import com.atmj.gsboot.services.machines.MachineService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

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
