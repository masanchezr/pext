package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.operations.OperationService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class ExpensesController {

	@Autowired
	private OperationService operationService;

	@RequestMapping(value = "/summaryexpenses")
	public ModelAndView searchexpenses() {
		ModelAndView model = new ModelAndView("searchexpenses");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/resultexpenses")
	public ModelAndView resultexpenses(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("resultexpenses");
		model.addAllObjects(operationService.findExpensesByMonth(searchForm.getDatefrom()));
		return model;
	}
}
