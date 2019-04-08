package com.gu.admin.controllers;

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
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
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

	public static final String VIEWUPDATEOPERATIONADMIN = "updateoperationadmin";

	@RequestMapping(value = "/admin/updateoperation{id}")
	public ModelAndView updateoperation(@PathVariable("id") long id) {
		OperationEntity operation = operationService.findById(id);
		ModelAndView model = new ModelAndView(VIEWUPDATEOPERATIONADMIN);
		model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsJsp.OPERATION, operation);
		return model;
	}

	@RequestMapping(value = "/admin/saveoperation")
	public ModelAndView saveoperation(@ModelAttribute(ConstantsJsp.OPERATION) OperationEntity operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		operationsValidator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWUPDATEOPERATIONADMIN);
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsJsp.OPERATION, operation);
		} else if (operationService.getOperationNotAllowed(operation) != null) {
			model.setViewName(VIEWUPDATEOPERATIONADMIN);
			model.addObject(Constants.MACHINES, machineService.searchAllMachinesOrder());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsJsp.OPERATION, operation);
			result.rejectValue(Constants.AMOUNT, "operationnotallowed");
		} else {
			model.addObject(ConstantsJsp.DAILY, operationService.update(operation));
			model.addObject(ConstantsJsp.DATEDAILY, operation.getCreationdate());
			model.setViewName("dailyadminarrows");
		}
		return model;
	}

	@RequestMapping(value = "/admin/searchticketsByDay")
	public ModelAndView searchticketsByDay() {
		ModelAndView model = new ModelAndView("searchticketsbyday");
		model.addObject("searchform", new SearchByDatesForm());
		return model;
	}
}
