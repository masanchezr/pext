package com.atmj.gsboot.admin.controllers;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.admin.forms.Safe;
import com.atmj.gsboot.dbaccess.entities.SafeEntity;
import com.atmj.gsboot.services.entrymoney.EntryMoneyService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class SafeController {

	@Autowired
	private EntryMoneyService safeService;

	@Autowired
	private ModelMapper mapper;

	private static final String FORMSALE = "safeForm";

	private static final String VIEWTOTALSAFE = "admin/safe/totalsafe";

	@GetMapping("/admin/newentrysafe")
	public ModelAndView newentrysafe() {
		ModelAndView model = new ModelAndView("admin/safe/safe");
		model.addObject(FORMSALE, new Safe());
		return model;
	}

	@PostMapping("/admin/savesafe")
	public ModelAndView savesafe(@Valid Safe safe, BindingResult result) {
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
	public ModelAndView saveentrymachine(@Valid Safe safe, BindingResult result) {
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
	public ModelAndView saveentrydirectmachine(@Valid Safe safe, BindingResult result) {
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
