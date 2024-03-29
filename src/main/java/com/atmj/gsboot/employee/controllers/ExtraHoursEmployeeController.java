package com.atmj.gsboot.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.employee.forms.ExtraHours;
import com.atmj.gsboot.services.extrahours.ExtraHourService;
import com.atmj.gsboot.services.users.User;
import com.atmj.gsboot.services.users.UserService;

@Controller
public class ExtraHoursEmployeeController {

	@Autowired
	private ExtraHourService extraHourService;

	@Autowired
	private UserService employeeservice;

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
			User employee = employeeservice.findUser(user);
			extrahours.setEmployee(employee);
			extraHourService.save(extrahours);
			model.setViewName("/employee/success");
		}
		return model;
	}

}
