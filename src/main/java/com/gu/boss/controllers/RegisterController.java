package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.register.RegisterService;
import com.gu.util.constants.ConstantsViews;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class RegisterController {

	@Autowired
	private RegisterService registerservice;

	@Autowired
	private SearchDatesFormValidator searchDatesFormValidator;

	@GetMapping("/register")
	public ModelAndView register(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm sdf, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		searchDatesFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsViews.FORMSEARCH, sdf);
			model.setViewName(ConstantsViews.VIEWSEARCHREGISTER);
			return model;
		} else {
			model.addObject("registers", registerservice.findByDates(sdf.getDatefrom(), sdf.getDateuntil()));
			model.setViewName(ConstantsViews.VIEWREGISTER);
		}
		return model;
	}

	@GetMapping("/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView(ConstantsViews.VIEWSEARCHREGISTER);
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}
}
