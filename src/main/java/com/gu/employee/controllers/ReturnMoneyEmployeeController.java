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

@Controller
public class ReturnMoneyEmployeeController {

	@Autowired
	private ReturnMoneyEmployeeService returnmoneyemployeeservice;

	@Autowired
	private EmployeeService employeeservice;

	@RequestMapping(value = "/employee/newreturn")
	public ModelAndView newreturn() {
		ModelAndView model = new ModelAndView("returnmoneyemployee");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnmoneyemployeeservice.findAdvanceByEmployee(employee);
		model.addObject("moneyadvance", moneyadvance);
		return model;
	}

	@RequestMapping(value = "/employee/savereturn")
	public ModelAndView savereturn(@ModelAttribute("incomeForm") ReturnMoneyEmployeeEntity returnme) {
		ModelAndView model = new ModelAndView();
		model.addObject("daily", returnmoneyemployeeservice.savereturn(returnme));
		model.setViewName("daily");
		return model;
	}

	@RequestMapping(value = "/employee/newmoneyadvance")
	public ModelAndView newmoneyadvance() {
		ModelAndView model = new ModelAndView("newmoneyadvance");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addObject("user", user);
		model.addObject("moneyadvance", new ReturnMoneyEmployeeEntity());
		return model;
	}

	@RequestMapping(value = "/employee/moneyadvance")
	public ModelAndView moneyadvance(@ModelAttribute("moneyadvance") ReturnMoneyEmployeeEntity returnme) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
		returnme.setEmployee(employee);
		model.addObject("daily", returnmoneyemployeeservice.savemoneyadvance(returnme));
		model.setViewName("daily");
		return model;
	}

}
