package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.extrahours.ExtraHourService;

@Controller
public class ExtraHoursBossController {

	@Autowired
	private ExtraHourService extraHourService;

	@GetMapping("/extrahours")
	public ModelAndView extrahours() {
		ModelAndView model = new ModelAndView("boss/extrahours");
		model.addObject("extrahours", extraHourService.findAll());
		return model;
	}
}
