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
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class TPVController {

	@Autowired
	private TPVService tpvservice;

	@Autowired
	private TPValidator tpvalidator;

	private static final String VIEWNEWTPV = "newtpv";
	private static final String FORMTPV = "tpv";

	@RequestMapping(value = "/employee/newtpv")
	public ModelAndView newtpv() {
		ModelAndView model = new ModelAndView(VIEWNEWTPV);
		model.addObject(FORMTPV, new TPVEntity());
		return model;
	}

	@RequestMapping(value = "/employee/tpv")
	public ModelAndView tpv(@ModelAttribute(FORMTPV) TPVEntity tpv, BindingResult result) {
		ModelAndView model = new ModelAndView();
		tpvalidator.validate(tpv, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWTPV);
			model.addObject(FORMTPV, tpv);
		} else {
			// miro si existe ya
			TPVEntity tpventity = tpvservice.findById(tpv);
			if (tpventity != null) {
				model.setViewName(VIEWNEWTPV);
				model.addObject(FORMTPV, tpv);
				result.rejectValue(Constants.IDTPV, "exists");
			} else {
				model.addObject(ConstantsJsp.DAILY, tpvservice.save(tpv));
				model.setViewName(ConstantsJsp.DAILY);
			}
		}
		return model;
	}
}
