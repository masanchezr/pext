package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.register.RegisterService;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class RegisterAdminController {
	@Autowired
	private RegisterService registerservice;

	@Autowired
	private SearchDatesFormValidator validator;

	@RequestMapping(value = "/admin/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView("searchregister");
		model.addObject("searchDateForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/admin/register")
	public ModelAndView register(@ModelAttribute("searchDateForm") SearchByDatesForm form, BindingResult result) {
		ModelAndView model = new ModelAndView();
		validator.validate(form, result);
		if (result.hasErrors()) {
			model.addObject("searchDatesForm", new SearchByDatesForm());
			model.setViewName("searchregister");
		} else {
			model.addObject("register", registerservice.findByDates(form.getDatefrom(), form.getDateuntil()));
			model.setViewName("registeradmin");
		}
		return model;

	}
}
