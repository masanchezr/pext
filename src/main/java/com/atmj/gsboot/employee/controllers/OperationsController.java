package com.atmj.gsboot.employee.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.OperationEntity;
import com.atmj.gsboot.dbaccess.entities.UserEntity;
import com.atmj.gsboot.forms.Operation;
import com.atmj.gsboot.services.awards.AwardService;
import com.atmj.gsboot.services.machines.MachineService;
import com.atmj.gsboot.services.operations.OperationService;
import com.atmj.gsboot.services.payments.PaymentService;
import com.atmj.gsboot.services.users.User;
import com.atmj.gsboot.services.users.UserService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.validators.OperationsValidator;

@Controller
public class OperationsController {

	@Autowired
	private AwardService awardService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private OperationService operationService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private UserService employeeService;

	@Autowired
	private OperationsValidator validator;

	@Autowired
	private ModelMapper mapper;

	private static final String VIEWNEWOPERATION = "employee/expenses/operations/newoperation";

	@GetMapping("/employee/newoperation")
	public ModelAndView newoperation() {
		ModelAndView model = new ModelAndView(VIEWNEWOPERATION);
		model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
		model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
		model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
		model.addObject(ConstantsViews.EMPLOYEES, employeeService.allUsersEnabled());
		model.addObject(ConstantsViews.OPERATION, new OperationEntity());
		return model;
	}

	@PostMapping("/employee/saveoperation")
	public ModelAndView saveoperation(Operation operation, BindingResult result) {
		ModelAndView model = new ModelAndView();
		OperationEntity op = mapper.map(operation, OperationEntity.class);
		validator.validate(operation, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWOPERATION);
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.EMPLOYEES, employeeService.allUsersEnabled());
			model.addObject(ConstantsViews.OPERATION, operation);
		} else if (operationService.getOperationNotAllowed(op) != null) {
			model.setViewName(VIEWNEWOPERATION);
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsViews.PAYMENTS, paymentService.findAllActive());
			model.addObject(ConstantsViews.AWARDS, awardService.searchAllAwardsActiveByOrder());
			model.addObject(ConstantsViews.EMPLOYEES, employeeService.allUsersEnabled());
			model.addObject(ConstantsViews.OPERATION, operation);
			result.rejectValue(Constants.AMOUNT, "operationnotallowed");
		} else {
			if (operation.getEmployee().getId().equals(Constants.NOBODY)) {
				String user = SecurityContextHolder.getContext().getAuthentication().getName();
				User employee = employeeService.findUser(user);
				operation.setEmployee(mapper.map(employee, UserEntity.class));
			}
			model.addObject(ConstantsViews.DAILY, operationService.save(op));
			model.setViewName("employee/daily/daily");
		}
		return model;
	}
}
