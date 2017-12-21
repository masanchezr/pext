package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.forms.SearchByDatesForm;
import com.gu.services.awards.AwardService;
import com.gu.services.machines.MachineService;
import com.gu.services.operations.OperationService;
import com.gu.services.payments.PaymentService;
import com.gu.validators.OperationsValidator;

@Controller
public class OperationsBossController {

	@Autowired
	private AwardService awardService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private OperationsValidator operationsValidator;

	@RequestMapping(value = "/updateoperation{id}")
	public ModelAndView updateoperationboss(@PathVariable("id") long id) {
		OperationEntity operation = operationService.findOne(id);
		ModelAndView model = new ModelAndView("updateoperationboss");
		model.addObject("machines", machineService.searchAllMachinesOrder());
		model.addObject("payments", paymentService.findAllActive());
		model.addObject("awards", awardService.searchAllAwardsActiveByOrder());
		model.addObject("operation", operation);
		return model;
	}

	@RequestMapping(value = "/saveoperation")
	public ModelAndView saveoperationboss(@ModelAttribute("operation") OperationEntity operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		operationsValidator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName("updateoperationboss");
			model.addObject("machines", machineService.searchAllMachinesOrder());
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("awards", awardService.searchAllAwardsActiveByOrder());
			model.addObject("operation", operation);
		} else {
			model.addObject("daily", operationService.update(operation));
			model.setViewName("success");
			// model.addObject("datedaily", new Date());
		}
		return model;
	}

	@RequestMapping(value = "/deleteoperation")
	public ModelAndView deleteoperation(@ModelAttribute("operation") OperationEntity operation) {
		ModelAndView model = new ModelAndView("success");
		operationService.delete(operation);
		return model;
	}

	@RequestMapping(value = "/searchrecharges")
	public ModelAndView searchRecharges() {
		ModelAndView model = new ModelAndView("searchrecharges");
		model.addObject("searchForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/recharges")
	public ModelAndView recharges(@ModelAttribute("searchForm") SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("recharges");
		model.addObject("recharges", operationService.recharges(searchForm.getDatefrom()));
		return model;
	}
}
