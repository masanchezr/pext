package com.gu.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.IncomeMachineEntity;
import com.gu.services.incomemachines.IncomeMachineService;
import com.gu.services.machines.MachineService;

@Controller
public class IncomeMachinesController {

	@Autowired
	private IncomeMachineService incomemachineService;

	@Autowired
	private MachineService machineservice;

	@RequestMapping(value = "/employee/newincomemachine")
	public ModelAndView newincomemachine() {
		ModelAndView model = new ModelAndView("incomemachine");
		model.addObject("machines", machineservice.searchAllMachinesOrder());
		model.addObject("incomeForm", new IncomeMachineEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincomemachine")
	public ModelAndView saveincomemachine(@ModelAttribute("incomeForm") IncomeMachineEntity imachine) {
		ModelAndView model = new ModelAndView("successemployee");
		model.addObject("daily", incomemachineService.save(imachine));
		model.setViewName("daily");
		model.addObject("datedaily", new Date());
		return model;
	}

}
