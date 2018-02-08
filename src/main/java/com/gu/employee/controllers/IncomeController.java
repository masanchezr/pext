package com.gu.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.employee.validators.IncomeValidator;
import com.gu.services.income.IncomeService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeservice;

	@Autowired
	private IncomeValidator incomeValidator;

	private static final String VIEWNEWINCOME = "newincome";

	@RequestMapping(value = "/employee/newincome")
	public ModelAndView newincome() {
		ModelAndView model = new ModelAndView(VIEWNEWINCOME);
		model.addObject(ConstantsJsp.FORMINCOME, new BarDrinkEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincome")
	public ModelAndView saveincome(@ModelAttribute(ConstantsJsp.FORMINCOME) BarDrinkEntity income,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		incomeValidator.validate(income, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWINCOME);
			model.addObject(ConstantsJsp.FORMINCOME, income);
		} else {
			model.addObject(ConstantsJsp.DAILY, incomeservice.save(income));
			model.setViewName(ConstantsJsp.DAILY);
			model.addObject(ConstantsJsp.DATEDAILY, new Date());
		}
		return model;

	}
}