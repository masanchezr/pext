package com.sboot.golden.admin.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.admin.forms.Safe;
import com.sboot.golden.admin.validators.SafeValidator;
import com.sboot.golden.dbaccess.entities.SafeEntity;
import com.sboot.golden.services.entrymoney.EntryMoneyService;
import com.sboot.golden.util.constants.ConstantsViews;

@Controller
public class SafeController {

	@Autowired
	private EntryMoneyService safeService;

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
	public ModelAndView savesafe(@Validated(SafeValidator.class) @ModelAttribute(FORMSALE) Safe safe,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("admin/safe/safe");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsViews.TOTALAMOUNT, safeService.saveAdd(mapper.map(safe, SafeEntity.class)));
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
	public ModelAndView saveentrymachine(@Validated(SafeValidator.class) @ModelAttribute(FORMSALE) Safe safe,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("admin/changemachine/newentrychangemachine");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsViews.TOTALAMOUNT, safeService.saveSub(mapper.map(safe, SafeEntity.class)));
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
	public ModelAndView saveentrydirectmachine(@Validated(SafeValidator.class) @ModelAttribute(FORMSALE) Safe safe,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("admin/changemachine/newentrydirect");
			model.addObject(FORMSALE, safe);
		} else {
			model.addObject(ConstantsViews.TOTALAMOUNT, safeService.saveSubDirect(mapper.map(safe, SafeEntity.class)));
			model.setViewName(VIEWTOTALSAFE);
		}
		return model;
	}

	@GetMapping("/admin/totalsafe")
	public ModelAndView totalsafe() {
		ModelAndView model = new ModelAndView(VIEWTOTALSAFE);
		model.addObject(ConstantsViews.TOTALAMOUNT, safeService.searchTotalSafe());
		return model;
	}
}
