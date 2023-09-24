package com.atmj.gsboot.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.operations.OperationService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class ExpensesController {

	@Autowired
	private OperationService operationService;

	@GetMapping("/summaryexpenses")
	public ModelAndView searchexpenses() {
		ModelAndView model = new ModelAndView("boss/expenses/searchexpenses");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/resultexpenses")
	public ModelAndView resultexpenses(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("boss/expenses/result");
		model.addAllObjects(operationService.findExpensesByMonth(searchForm.getDatefrom()));
		return model;
	}
}
