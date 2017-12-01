package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.employee.validators.ChangeMachineValidator;
import com.gu.services.awards.AwardService;
import com.gu.services.changemachine.ChangeMachineService;
import com.gu.services.machines.MachineService;

@Controller
public class ChangeMachineController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private AwardService awardservice;

	@Autowired
	private ChangeMachineValidator changeMachineValidator;

	@RequestMapping(value = "/employee/newpaymentchangemachine")
	public ModelAndView newpaymentchangemachine() {
		ModelAndView model = new ModelAndView("newpaymentchangemachine");
		model.addObject("machines", machineService.searchMachinesOrder());
		model.addObject("awards", awardservice.getAwardsChangeMachine());
		model.addObject("changemachine", new ChangeMachineEntity());
		return model;
	}

	@RequestMapping(value = "/employee/savepaymentchangemachine")
	public ModelAndView savepaymentchangemachine(@ModelAttribute("changemachine") ChangeMachineEntity cm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		changeMachineValidator.validate(cm, result);
		if (result.hasErrors()) {
			model.addObject("machines", machineService.searchMachinesOrder());
			model.addObject("awards", awardservice.getAwardsChangeMachine());
			model.addObject("changemachine", cm);
			model.setViewName("newpaymentchangemachine");
		} else {
			ChangeMachineEntity cmentity = changeMachineService.findOne(cm.getIdchangemachine());
			if (cmentity != null) {
				model.addObject("machines", machineService.searchMachinesOrder());
				model.addObject("awards", awardservice.getAwardsChangeMachine());
				model.addObject("changemachine", cm);
				model.setViewName("newpaymentchangemachine");
				result.rejectValue("idchangemachine", "selectid");
			} else {
				model.addObject("daily", changeMachineService.save(cm));
				model.setViewName("daily");
			}
		}
		return model;
	}
}
