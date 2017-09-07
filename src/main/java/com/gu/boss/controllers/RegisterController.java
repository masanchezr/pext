package com.gu.boss.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.register.RegisterService;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerservice;

	@RequestMapping(value = "/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView("register");
		model.addObject("registers", registerservice.findByDate(new Date()));
		return model;
	}
}
