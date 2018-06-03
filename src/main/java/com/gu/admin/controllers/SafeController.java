package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.SafeValidator;
import com.gu.dbaccess.entities.SafeEntity;
import com.gu.services.entrymoney.EntryMoneyService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class SafeController {

	@Autowired
	private EntryMoneyService safeService;

	@Autowired
	private SafeValidator safevalidator;

	private static final String VIEWTOTALSAFE = "totalsafe";
	private static final String FORMSALE = "saleForm";

	@RequestMapping(value = "/admin/newentrysafe")
	public ModelAndView newentrysafe() {
		ModelAndView model = new ModelAndView("safe");
		model.addObject(FORMSALE, new SafeEntity());
		return model;
	}

	@RequestMapping(value = "/admin/savesafe")
	public ModelAndView savesafe(@ModelAttribute(FORMSALE) SafeEntity safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("safe");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.saveAdd(safe));
			model.setViewName(VIEWTOTALSAFE);
		}
		return model;
	}

	@RequestMapping(value = "/admin/newentrymachine")
	public ModelAndView newentrymachine() {
		ModelAndView model = new ModelAndView("newentrychangemachine");
		model.addObject(FORMSALE, new SafeEntity());
		return model;
	}

	@RequestMapping(value = "/admin/saveentrymachine")
	public ModelAndView saveentrymachine(@ModelAttribute(FORMSALE) SafeEntity safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("newentrychangemachine");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.saveSub(safe));
			model.setViewName(VIEWTOTALSAFE);
		}
		return model;
	}

	@RequestMapping(value = "/admin/totalsafe")
	public ModelAndView totalsafe() {
		ModelAndView model = new ModelAndView(VIEWTOTALSAFE);
		model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.searchTotalSafe());
		return model;
	}
}
