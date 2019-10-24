package com.gu.admin.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	private OperationsValidator operationsValidator;

	@Autowired
	private Mapper mapper;

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
	public ModelAndView saveoperation(@ModelAttribute(ConstantsViews.OPERATION) Operation operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		OperationEntity op = mapper.map(operation, OperationEntity.class);
		operationsValidator.validate(operation, result);
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
			model.addObject(ConstantsViews.DATEDAILY, operation.getCreationdate());
			model.setViewName(ConstantsViews.VIEWDAILYADMINARROWS);
		}
		return model;
	}
}
