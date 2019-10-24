package com.gu.boss.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.forms.Operation;
import com.gu.services.awards.AwardService;
import com.gu.services.machines.MachineService;
import com.gu.services.operations.OperationService;
import com.gu.services.payments.PaymentService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsViews;
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

	@Autowired
	private Mapper mapper;

	@GetMapping("/updateoperation{id}")
	public ModelAndView updateoperationboss(@PathVariable("id") long id) {
		OperationEntity operation = operationService.findById(id);
		ModelAndView model = new ModelAndView("boss/newoperation");
		model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsViews.OPERATION, operation);
		return model;
	}

	@PostMapping("/saveoperation")
	public ModelAndView saveoperationboss(@ModelAttribute(ConstantsViews.OPERATION) Operation operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		operationsValidator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName("boss/newoperation");
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.OPERATION, operation);
		} else {
			model.addObject(ConstantsViews.DAILY, operationService.update(mapper.map(operation, OperationEntity.class)));
			model.setViewName("boss/success");
		}
		return model;
	}

	@GetMapping("/deleteoperation")
	public ModelAndView deleteoperation(@ModelAttribute(ConstantsViews.OPERATION) Operation operation) {
		ModelAndView model = new ModelAndView("boss/success");
		operationService.delete(mapper.map(operation, OperationEntity.class));
		return model;
	}
}
