package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.register.FicticionalRegisterService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class RegisterAdminController {

	@Autowired
	private FicticionalRegisterService ficticionalRegisterService;

	@Autowired
	private SearchDatesFormValidator searchDatesFormValidator;

	@GetMapping("/admin/searchregister")
	public ModelAndView searchregister() {
		ModelAndView model = new ModelAndView("admin/registers/searchregisters");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/admin/register")
	public ModelAndView register(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm sdf, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		searchDatesFormValidator.validate(sdf, arg1);
		if (arg1.hasErrors()) {
			model = new ModelAndView();
			model.addObject(ConstantsJsp.FORMSEARCH, sdf);
			model.setViewName("admin/registers/searchregisters");
			return model;
		} else {
			model.addObject("registers", ficticionalRegisterService.findByDates(sdf.getDatefrom(), sdf.getDateuntil()));
			model.setViewName("admin/registers/register");
		}
		return model;
	}

}
