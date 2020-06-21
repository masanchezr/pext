package com.gu.employee.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.employee.forms.ExtraHours;
import com.gu.services.extrahours.ExtraHourService;

@Controller
public class ExtraHoursController {

	private ExtraHourService extraHourService;

	@GetMapping("/employee/extrahours")
	public ModelAndView extrahours() {
		ModelAndView model = new ModelAndView("extrahours");
		model.addObject("extrahours", new ExtraHours());
		return model;
	}

	@RequestMapping("/employee/extrahoursave")
	public ModelAndView extrahoursave(@ModelAttribute("extrahours") ExtraHours extrahours) {
		ModelAndView model = new ModelAndView();
		extraHourService.save(extrahours);
		return model;
	}

}
