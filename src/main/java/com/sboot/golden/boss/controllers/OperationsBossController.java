package com.sboot.golden.boss.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.OperationEntity;
import com.sboot.golden.forms.Operation;
import com.sboot.golden.services.awards.AwardService;
import com.sboot.golden.services.machines.MachineService;
import com.sboot.golden.services.operations.OperationService;
import com.sboot.golden.services.payments.PaymentService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.validators.OperationsValidator;

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
	private ModelMapper mapper;

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
	public ModelAndView saveoperationboss(
			@Validated(OperationsValidator.class) @ModelAttribute(ConstantsViews.OPERATION) Operation operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("boss/newoperation");
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.OPERATION, operation);
		} else {
			model.addObject(ConstantsViews.DAILY,
					operationService.update(mapper.map(operation, OperationEntity.class)));
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
