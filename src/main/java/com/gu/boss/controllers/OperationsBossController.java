package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.services.awards.AwardService;
import com.gu.services.machines.MachineService;
import com.gu.services.operations.OperationService;
import com.gu.services.payments.PaymentService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
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
		OperationEntity operation = operationService.findById(id);
		ModelAndView model = new ModelAndView("updateoperationboss");
		model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsJsp.OPERATION, operation);
		return model;
	}

	@RequestMapping(value = "/saveoperation")
	public ModelAndView saveoperationboss(@ModelAttribute(ConstantsJsp.OPERATION) OperationEntity operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		operationsValidator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName("updateoperationboss");
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsJsp.OPERATION, operation);
		} else {
			model.addObject(ConstantsJsp.DAILY, operationService.update(operation));
			model.setViewName(ConstantsJsp.SUCCESS);
		}
		return model;
	}

	@RequestMapping(value = "/deleteoperation")
	public ModelAndView deleteoperation(@ModelAttribute(ConstantsJsp.OPERATION) OperationEntity operation) {
		ModelAndView model = new ModelAndView(ConstantsJsp.SUCCESS);
		operationService.delete(operation);
		return model;
	}
}
