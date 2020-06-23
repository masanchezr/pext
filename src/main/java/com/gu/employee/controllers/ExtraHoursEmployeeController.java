package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.employee.forms.ExtraHours;
import com.gu.services.employees.EmployeeService;
import com.gu.services.extrahours.ExtraHourService;

@Controller
public class ExtraHoursEmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	@Autowired
	private ExtraHourService extraHourService;

	@GetMapping("/employee/extrahours")
	public ModelAndView extrahours() {
		ModelAndView model = new ModelAndView("/employee/extrahours");
		model.addObject("extrahours", new ExtraHours());
		return model;
	}

	@PostMapping("/employee/extrahoursave")
	public ModelAndView extrahoursave(@ModelAttribute("extrahours") ExtraHours extrahours, BindingResult errors) {
		ModelAndView model = new ModelAndView();
		if (errors.hasErrors()) {
			model.setViewName("/employee/extrahours");
			model.addObject("extrahours", extrahours);
		} else {
			String user = SecurityContextHolder.getContext().getAuthentication().getName();
			EmployeeEntity employee = employeeservice.getEmployeeByUserName(user);
			extrahours.setEmployee(employee);
			extraHourService.save(extrahours);
			model.setViewName("/employee/success");
		}
		return model;
	}

}
