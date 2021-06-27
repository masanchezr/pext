package com.sboot.golden.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.forms.SearchByDatesForm;
import com.sboot.golden.services.entrymoney.EntryMoneyService;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.validators.SearchDatesFormValidator;

@Controller
public class SafeBossController {

	@Autowired
	private EntryMoneyService safeService;

	private static final String VIEWSEARCHENTRYSORTSAFE = "boss/safe/searchentrysortsafe";

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
	public ModelAndView resultentrysortsafe(
			@Validated(SearchDatesFormValidator.class) @ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
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
