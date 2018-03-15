package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.register.RegisterService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class RegisterController {

	private static final String VIEWREGISTER = "register";

	private static final String VIEWSEARCHREGISTER = "searchregisters";

	@Autowired
	private RegisterService registerservice;

	@Autowired
	private SearchDatesFormValidator searchDatesFormValidator;

	@RequestMapping(value = "/register")
	public ModelAndView searchregister(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm sdf,
			BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		searchDatesFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsJsp.FORMSEARCH, sdf);
			model.setViewName(VIEWSEARCHREGISTER);
			return model;
		} else {
			model.addObject("registers", registerservice.findByDates(sdf.getDatefrom(), sdf.getDateuntil()));
			model.setViewName(VIEWREGISTER);
		}
		return model;
	}

	@RequestMapping(value = "/searchregister")
	public ModelAndView searchCalculateDailies() {
		ModelAndView model = new ModelAndView(VIEWSEARCHREGISTER);
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}
}
