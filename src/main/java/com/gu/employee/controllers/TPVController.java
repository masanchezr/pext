package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.TPVEntity;
import com.gu.employee.validators.TPValidator;
import com.gu.services.tpv.TPVService;

@Controller
public class TPVController {

	@Autowired
	private TPVService tpvservice;

	@Autowired
	private TPValidator tpvalidator;

	@RequestMapping(value = "/employee/newtpv")
	public ModelAndView newtpv() {
		ModelAndView model = new ModelAndView("newtpv");
		model.addObject("tpv", new TPVEntity());
		return model;
	}

	@RequestMapping(value = "/employee/tpv")
	public ModelAndView tpv(@ModelAttribute("tpv") TPVEntity tpv, BindingResult result) {
		ModelAndView model = new ModelAndView();
		tpvalidator.validate(tpv, result);
		if (result.hasErrors()) {
			model.setViewName("newtpv");
			model.addObject("tpv", tpv);
		} else {
			// miro si existe ya
			TPVEntity tpventity = tpvservice.findById(tpv);
			if (tpventity != null) {
				model.setViewName("newtpv");
				model.addObject("tpv", tpv);
				result.rejectValue("idtpv", "exists");
			} else {
				model.addObject("daily", tpvservice.save(tpv));
				model.setViewName("daily");
			}
		}
		return model;
	}
}
