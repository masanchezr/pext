package com.bar.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bar.forms.SearchDatesForm;
import com.bar.services.income.IncomeService;

@Controller
public class IncomeBossController {

	@Autowired
	private IncomeService incomeService;

	@RequestMapping(value = "/summaryincome")
	public ModelAndView summaryincome() {
		ModelAndView model = new ModelAndView("summaryincome");
		model.addObject("searchForm", new SearchDatesForm());
		return model;
	}

	@RequestMapping(value = "/resultincome")
	public ModelAndView resultincome(@ModelAttribute("searchForm") SearchDatesForm searchForm) {
		ModelAndView model = new ModelAndView("resultincome");
		model.addObject("totalamount", incomeService.findIncomeByMonth(searchForm.getDatefrom()));
		return model;
	}
}
