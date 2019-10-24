package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.operations.OperationService;
import com.gu.util.constants.ConstantsViews;

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
