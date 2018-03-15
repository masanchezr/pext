package com.gu.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.services.employees.EmployeeService;
import com.gu.services.returnmoneyemployees.ReturnMoneyEmployeeService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class ReturnMoneyEmployeeController {

	@Autowired
	private ReturnMoneyEmployeeService returnmoneyemployeeservice;

	@Autowired
	private EmployeeService employeeservice;

	private static final String FORMMONEYADVANCE = "moneyadvance";

	@RequestMapping(value = "/employee/newreturn")
	public ModelAndView newreturn() {
		ModelAndView model = new ModelAndView("returnmoneyemployee");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnmoneyemployeeservice.findAdvanceByEmployee(employee);
		model.addObject(FORMMONEYADVANCE, moneyadvance);
		model.addObject(ConstantsJsp.FORMINCOME, new ReturnMoneyEmployeeEntity());
		return model;
	}

	@RequestMapping(value = "/employee/savereturn")
	public ModelAndView savereturn(@ModelAttribute(ConstantsJsp.FORMINCOME) ReturnMoneyEmployeeEntity returnme) {
		ModelAndView model = new ModelAndView();
		model.addObject(ConstantsJsp.DAILY, returnmoneyemployeeservice.savereturn(returnme));
		model.setViewName(ConstantsJsp.DAILY);
		return model;
	}

	@RequestMapping(value = "/employee/newmoneyadvance")
	public ModelAndView newmoneyadvance() {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject(ConstantsJsp.USER, user);
		model.addObject(FORMMONEYADVANCE, new ReturnMoneyEmployeeEntity());
		model.setViewName("newmoneyadvance");
		return model;
	}

	@RequestMapping(value = "/employee/moneyadvance")
	public Object moneyadvance(@ModelAttribute(FORMMONEYADVANCE) ReturnMoneyEmployeeEntity returnme) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
		returnme.setEmployee(employee);
		// miramos primero que no supere el importe
		if (returnmoneyemployeeservice.isAllowedAdvances(returnme)) {
			model.addObject(ConstantsJsp.DAILY, returnmoneyemployeeservice.savemoneyadvance(returnme));
			model.setViewName(ConstantsJsp.DAILY);
			return model;
		} else {
			model.setViewName("notmoney");
			return model;
		}
	}
}
