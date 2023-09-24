package com.atmj.gsboot.boss.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.OperationEntity;
import com.atmj.gsboot.forms.Operation;
import com.atmj.gsboot.services.awards.AwardService;
import com.atmj.gsboot.services.machines.MachineService;
import com.atmj.gsboot.services.operations.OperationService;
import com.atmj.gsboot.services.payments.PaymentService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.validators.OperationsValidator;

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
	private OperationsValidator validator;

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
	public ModelAndView saveoperationboss(Operation operation, BindingResult result) {
		ModelAndView model = new ModelAndView();
		validator.validate(operation, result);
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
