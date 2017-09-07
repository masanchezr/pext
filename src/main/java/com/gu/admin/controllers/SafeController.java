package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.SafeValidator;
import com.gu.dbaccess.entities.SafeEntity;
import com.gu.services.safe.SafeService;

@Controller
public class SafeController {

	@Autowired
	private SafeService safeService;

	@Autowired
	private SafeValidator safevalidator;

	@RequestMapping(value = "/admin/newentrysafe")
	public ModelAndView newentrysafe() {
		ModelAndView model = new ModelAndView("safe");
		model.addObject("safeForm", new SafeEntity());
		return model;
	}

	@RequestMapping(value = "/admin/savesafe")
	public ModelAndView savesafe(@ModelAttribute("safeForm") SafeEntity safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("safe");
			model.addObject("safeForm", safe);
		} else {
			model.addObject("totalamount", safeService.saveAdd(safe));
			model.setViewName("totalsafe");
		}
		return model;
	}

	@RequestMapping(value = "/admin/newentrymachine")
	public ModelAndView newentrymachine() {
		ModelAndView model = new ModelAndView("newentrychangemachine");
		model.addObject("safeForm", new SafeEntity());
		return model;
	}

	@RequestMapping(value = "/admin/saveentrymachine")
	public ModelAndView saveentrymachine(@ModelAttribute("safeForm") SafeEntity safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("newentrychangemachine");
			model.addObject("safeForm", safe);
		} else {
			model.addObject("totalamount", safeService.saveSub(safe));
			model.setViewName("totalsafe");
		}
		return model;
	}

	@RequestMapping(value = "/admin/totalsafe")
	public ModelAndView totalsafe() {
		ModelAndView model = new ModelAndView("totalsafe");
		model.addObject("totalamount", safeService.searchTotal());
		return model;
	}
}
