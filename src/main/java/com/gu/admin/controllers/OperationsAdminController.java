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
import com.gu.util.date.DateUtil;
import com.gu.validators.OperationsValidator;
import com.gu.validators.SearchDatesFormValidator;

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
	private SearchDatesFormValidator searchDatesFormValidator;

	@RequestMapping(value = "/admin/updateoperation{id}")
	public ModelAndView updateoperation(@PathVariable("id") long id) {
		OperationEntity operation = operationService.findById(id);
		ModelAndView model = new ModelAndView("updateoperationadmin");
		model.addObject("machines", machineService.searchAllMachinesOrder());
		model.addObject("payments", paymentService.findAllActive());
		model.addObject("awards", awardService.searchAllAwardsActiveByOrder());
		model.addObject("operation", operation);
		return model;
	}

	@RequestMapping(value = "/admin/saveoperation")
	public ModelAndView saveoperation(@ModelAttribute("operation") OperationEntity operation, BindingResult result) {
		ModelAndView model = new ModelAndView();
		operationsValidator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName("updateoperationadmin");
			model.addObject("machines", machineService.searchAllMachinesOrder());
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("awards", awardService.searchAllAwardsActiveByOrder());
			model.addObject("operation", operation);
		} else if (operationService.getOperationNotAllowed(operation) != null) {
			model.setViewName("updateoperationadmin");
			model.addObject("machines", machineService.searchAllMachinesOrder());
			model.addObject("payments", paymentService.findAllActive());
			model.addObject("awards", awardService.searchAllAwardsActiveByOrder());
			model.addObject("operation", operation);
			result.rejectValue("amount", "operationnotallowed");
		} else {
			model.addObject("daily", operationService.update(operation));
			model.setViewName("dailyadmin");
		}
		return model;
	}

	@RequestMapping(value = "/admin/searchticketsByDay")
	public ModelAndView searchticketsByDay() {
		ModelAndView model = new ModelAndView("searchticketsbyday");
		model.addObject("searchform", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/admin/ticketsByDay")
	public ModelAndView ticketsByDay(@ModelAttribute("searchform") SearchByDatesForm date, BindingResult result) {
		ModelAndView model = new ModelAndView();
		searchDatesFormValidator.validate(date, result);
		if (result.hasErrors()) {
			model.setViewName("searchticketsbyday");
		} else {
			model.setViewName("ticketsbyday");
			model.addAllObjects(operationService.ticketsByDay(DateUtil.getDate(date.getDatefrom())));
		}
		return model;
	}
}
