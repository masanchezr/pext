package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.OperationEntity;
import com.gu.services.awards.AwardService;
import com.gu.services.employees.EmployeeService;
import com.gu.services.machines.MachineService;
import com.gu.services.operations.OperationService;
import com.gu.services.payments.PaymentService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.validators.OperationsValidator;

@Controller
public class OperationsController {

	@Autowired
	private AwardService awardService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private OperationsValidator operationsValidator;

	private static final String VIEWNEWOPERATION = "newoperation";

	@RequestMapping(value = "/employee/newoperation")
	public ModelAndView newoperation() {
		ModelAndView model = new ModelAndView(VIEWNEWOPERATION);
		model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
		model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsJsp.EMPLOYEES, employeeService.allEmployeesActives());
		model.addObject(ConstantsJsp.OPERATION, new OperationEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveoperation")
	public ModelAndView saveoperation(@ModelAttribute(ConstantsJsp.OPERATION) OperationEntity operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		operationsValidator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWOPERATION);
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsJsp.EMPLOYEES, employeeService.allEmployeesActives());
			model.addObject(ConstantsJsp.OPERATION, operation);
		} else if (operationService.getOperationNotAllowed(operation) != null) {
			model.setViewName(VIEWNEWOPERATION);
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsJsp.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsJsp.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsJsp.EMPLOYEES, employeeService.allEmployeesActives());
			model.addObject(ConstantsJsp.OPERATION, operation);
			result.rejectValue(Constants.AMOUNT, "operationnotallowed");
		} else {
			if (operation.getEmployee().getIdemployee().equals(1L)) {
				String user = SecurityContextHolder.getContext().getAuthentication().getName();
				EmployeeEntity employee = employeeService.getEmployeeByUserName(user);
				operation.setEmployee(employee);
			}
			model.addObject(ConstantsJsp.DAILY, operationService.save(operation));
			model.setViewName(ConstantsJsp.DAILY);
		}
		return model;
	}
}
