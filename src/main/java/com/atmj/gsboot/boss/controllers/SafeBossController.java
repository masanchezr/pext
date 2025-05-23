package com.atmj.gsboot.boss.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.entrymoney.EntryMoneyService;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.validators.SearchDatesFormValidator;

@Controller
public class SafeBossController {

	private EntryMoneyService safeService;

	private SearchDatesFormValidator validator;

	private static final String VIEWSEARCHENTRYSORTSAFE = "boss/safe/searchentrysortsafe";

	public SafeBossController(EntryMoneyService safeService, SearchDatesFormValidator validator) {
		this.safeService = safeService;
		this.validator = validator;
	}

	@GetMapping("/safetotal")
	public ModelAndView safetotal() {
		ModelAndView model = new ModelAndView("boss/safe/safe");
		model.addObject(ConstantsViews.TOTALAMOUNT, safeService.searchTotalSafe());
		return model;
	}

	@GetMapping("/searchentrysortsafe")
	public ModelAndView searchbydates() {
		ModelAndView model = new ModelAndView(VIEWSEARCHENTRYSORTSAFE);
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/resultentrysortsafe")
	public ModelAndView resultentrysortsafe(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		validator.validate(searchForm, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHENTRYSORTSAFE);
			model.addObject(ConstantsViews.FORMSEARCH, searchForm);
		} else {
			model.addObject("resultsearch", safeService.searchByDates(searchForm.getDatefrom()));
			model.setViewName("boss/safe/resultentrysortsafe");
		}
		return model;
	}
}
