package com.atmj.gsboot.admin.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public class OperationsAdminController {

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

	public static final String VIEWUPDATEOPERATIONADMIN = "admin/newoperation";

	@GetMapping("/admin/updateoperation{id}")
	public ModelAndView updateoperation(@PathVariable("id") long id) {
		OperationEntity operation = operationService.findById(id);
		ModelAndView model = new ModelAndView(VIEWUPDATEOPERATIONADMIN);
		model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsViews.OPERATION, operation);
		return model;
	}

	@GetMapping("/admin/saveoperation")
	public ModelAndView saveoperation(Operation operation, BindingResult result) {
		ModelAndView model = new ModelAndView();
		OperationEntity op = mapper.map(operation, OperationEntity.class);
		validator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWUPDATEOPERATIONADMIN);
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.OPERATION, operation);
		} else if (operationService.getOperationNotAllowed(op) != null) {
			model.setViewName(VIEWUPDATEOPERATIONADMIN);
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.OPERATION, operation);
			result.rejectValue(Constants.AMOUNT, "operationnotallowed");
		} else {
			model.addObject(ConstantsViews.DAILY, operationService.update(op));
			model.setViewName(ConstantsViews.VIEWDAILYADMINARROWS);
		}
		return model;
	}
}
