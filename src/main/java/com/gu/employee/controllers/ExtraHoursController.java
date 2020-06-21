package com.gu.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.employee.forms.ExtraHours;
import com.gu.services.extrahours.ExtraHourService;

@Controller
public class ExtraHoursController {

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
			extraHourService.save(extrahours);
			model.setViewName("/employee/success");
		}
		return model;
	}

}
