package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
		model.addObject("employees", employeeservice.allEmployeesActives());
		model.addObject("incomeForm", new ReturnMoneyEmployeeEntity());
		return model;
	}

	@RequestMapping(value = "/employee/savereturn")
	public ModelAndView savereturn(@ModelAttribute("incomeForm") ReturnMoneyEmployeeEntity returnme) {
		ModelAndView model = new ModelAndView();
		model.addObject("daily", returnmoneyemployeeservice.save(returnme));
		model.setViewName("daily");
		return model;
	}
}
