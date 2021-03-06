package com.sboot.golden.employee.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.OperationEntity;
import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.forms.Operation;
import com.sboot.golden.services.awards.AwardService;
import com.sboot.golden.services.employees.EmployeeService;
import com.sboot.golden.services.machines.MachineService;
import com.sboot.golden.services.operations.OperationService;
import com.sboot.golden.services.payments.PaymentService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.validators.OperationsValidator;

@Controller
public class OperationsController {

	@Autowired
	private AwardService awardService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private Mapper mapper;

	private static final String VIEWNEWOPERATION = "employee/expenses/operations/newoperation";

	@GetMapping("/employee/newoperation")
	public ModelAndView newoperation() {
		ModelAndView model = new ModelAndView(VIEWNEWOPERATION);
		model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsViews.EMPLOYEES, employeeService.allEmployeesActives());
		model.addObject(ConstantsViews.OPERATION, new OperationEntity());
		return model;
	}

	@PostMapping("/employee/saveoperation")
	public ModelAndView saveoperation(
			@Validated(OperationsValidator.class) @ModelAttribute(ConstantsViews.OPERATION) Operation operation,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		OperationEntity op = mapper.map(operation, OperationEntity.class);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWOPERATION);
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.EMPLOYEES, employeeService.allEmployeesActives());
			model.addObject(ConstantsViews.OPERATION, operation);
		} else if (operationService.getOperationNotAllowed(op) != null) {
			model.setViewName(VIEWNEWOPERATION);
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.EMPLOYEES, employeeService.allEmployeesActives());
			model.addObject(ConstantsViews.OPERATION, operation);
			result.rejectValue(Constants.AMOUNT, "operationnotallowed");
		} else {
			if (operation.getEmployee().getId().equals(Constants.NOBODY)) {
				String user = SecurityContextHolder.getContext().getAuthentication().getName();
				UserEntity employee = employeeService.getEmployeeByUserName(user);
				operation.setEmployee(employee);
			}
			model.addObject(ConstantsViews.DAILY, operationService.save(op));
			model.setViewName("employee/daily/daily");
		}
		return model;
	}
}
