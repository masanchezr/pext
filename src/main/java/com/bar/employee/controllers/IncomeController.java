package com.bar.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bar.dbaccess.entities.IncomeEntity;
import com.bar.services.income.IncomeService;
import com.bar.validators.IncomeValidator;

@Controller
public class IncomeController {

	@Autowired
	private IncomeService incomeservice;

	@Autowired
	private IncomeValidator incomeValidator;

	@RequestMapping(value = "/employee/newincome")
	public ModelAndView newincome() {
		ModelAndView model = new ModelAndView("newincome");
		model.addObject("incomeForm", new IncomeEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincome")
	public ModelAndView saveincome(@ModelAttribute("incomeForm") IncomeEntity income, BindingResult result) {
		ModelAndView model = new ModelAndView();
		incomeValidator.validate(income, result);
		if (result.hasErrors()) {
			model.setViewName("newincome");
			model.addObject("incomeForm", income);
		} else {
			model.addObject("daily", incomeservice.save(income));
			model.setViewName("daily");
			model.addObject("datedaily", new Date());
		}
		return model;

	}
}
