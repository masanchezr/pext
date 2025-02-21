package com.atmj.gsboot.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.boss.forms.CollectionForm;
import com.atmj.gsboot.services.operations.OperationService;
import com.atmj.gsboot.services.takings.TakeService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class ExpensesController {

	@Autowired
	private OperationService operationService;

	@Autowired
	private TakeService takeService;

	@GetMapping("/summaryexpenses")
	public ModelAndView searchexpenses() {
		ModelAndView model = new ModelAndView("boss/expenses/searchexpenses");
		model.addObject(ConstantsViews.MODELCOLLECTION, new CollectionForm());
		model.addObject(Constants.TAKINGS, takeService.getAllTakings());
		return model;
	}

	@PostMapping("/resultexpenses")
	public ModelAndView resultexpenses(@ModelAttribute(ConstantsViews.MODELCOLLECTION) CollectionForm c) {
		ModelAndView model = new ModelAndView("boss/expenses/result");
		Long id = c.getId();
		model.addAllObjects(
				operationService.findExpenses(takeService.getFrom(id), takeService.findById(id).getTakedate()));
		return model;
	}
}
