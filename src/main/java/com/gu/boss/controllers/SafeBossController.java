package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.entrymoney.EntryMoneyService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.validators.SearchDatesFormValidator;

@Controller
public class SafeBossController {

	@Autowired
	private EntryMoneyService safeService;

	@Autowired
	private SearchDatesFormValidator searchdatesformvalidator;

	private static final String VIEWSEARCHENTRYSORTSAFE = "searchentrysortsafe";

	@RequestMapping(value = "/safetotal")
	public ModelAndView safetotal() {
		ModelAndView model = new ModelAndView("safetotal");
		model.addObject("totalamount", safeService.searchTotalSafe());
		return model;
	}

	@RequestMapping(value = "/searchentrysortsafe")
	public ModelAndView searchbydates() {
		ModelAndView model = new ModelAndView(VIEWSEARCHENTRYSORTSAFE);
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/resultentrysortsafe")
	public ModelAndView resultentrysortsafe(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		searchdatesformvalidator.validate(searchForm, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWSEARCHENTRYSORTSAFE);
			model.addObject(ConstantsJsp.FORMSEARCH, searchForm);
		} else {
			model.addObject("resultsearch", safeService.searchByDates(searchForm.getDatefrom()));
			model.setViewName("resultentrysortsafe");
		}
		return model;
	}
}
