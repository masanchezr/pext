package com.gu.admin.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.forms.Safe;
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

	@Autowired
	private Mapper mapper;

	private static final String FORMSALE = "safeForm";

	private static final String VIEWTOTALSAFE = "admin/safe/totalsafe";

	@GetMapping("/admin/newentrysafe")
	public ModelAndView newentrysafe() {
		ModelAndView model = new ModelAndView("admin/safe/safe");
		model.addObject(FORMSALE, new Safe());
		return model;
	}

	@PostMapping("/admin/savesafe")
	public ModelAndView savesafe(@ModelAttribute(FORMSALE) Safe safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("admin/safe/safe");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.saveAdd(mapper.map(safe, SafeEntity.class)));
			model.setViewName(VIEWTOTALSAFE);
		}
		return model;
	}

	@GetMapping("/admin/newentrymachine")
	public ModelAndView newentrymachine() {
		ModelAndView model = new ModelAndView("admin/changemachine/newentrychangemachine");
		model.addObject(FORMSALE, new SafeEntity());
		return model;
	}

	@PostMapping("/admin/saveentrymachine")
	public ModelAndView saveentrymachine(@ModelAttribute(FORMSALE) Safe safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("admin/changemachine/newentrychangemachine");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.saveSub(mapper.map(safe, SafeEntity.class)));
			model.setViewName(VIEWTOTALSAFE);
		}
		return model;
	}

	@GetMapping("/admin/newentrydirect")
	public ModelAndView newentrydirectmachine() {
		ModelAndView model = new ModelAndView("admin/changemachine/newentrydirect");
		model.addObject(FORMSALE, new Safe());
		return model;
	}

	@PostMapping("/admin/saveentrydirect")
	public ModelAndView saveentrydirectmachine(@ModelAttribute(FORMSALE) Safe safe, BindingResult result) {
		ModelAndView model = new ModelAndView();
		safevalidator.validate(safe, result);
		if (result.hasErrors()) {
			model.setViewName("admin/changemachine/newentrydirect");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.saveSubDirect(mapper.map(safe, SafeEntity.class)));
			model.setViewName(VIEWTOTALSAFE);
		}
		return model;
	}

	@GetMapping("/admin/totalsafe")
	public ModelAndView totalsafe() {
		ModelAndView model = new ModelAndView(VIEWTOTALSAFE);
		model.addObject(ConstantsJsp.TOTALAMOUNT, safeService.searchTotalSafe());
		return model;
	}
}
