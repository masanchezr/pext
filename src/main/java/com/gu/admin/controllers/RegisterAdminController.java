package com.gu.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.services.register.RegisterService;

@Controller
public class RegisterAdminController {
	@Autowired
	private RegisterService registerservice;

	@RequestMapping(value = "/admin/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView("registeradmin");
		model.addObject("registers", registerservice.findByDate(new Date()));
		return model;
	}
}
