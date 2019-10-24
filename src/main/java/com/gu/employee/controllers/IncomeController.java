package com.gu.employee.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.employee.forms.BarDrink;
import com.gu.employee.validators.IncomeValidator;
import com.gu.services.dailies.DailyService;
import com.gu.services.income.IncomeService;
import com.gu.util.constants.ConstantsViews;
import com.gu.util.date.DateUtil;

@Controller
public class IncomeController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeService incomeservice;

	@Autowired
	private IncomeValidator incomeValidator;

	@Autowired
	private Mapper mapper;

	private static final String VIEWNEWINCOME = "employee/income/newincome";

	@GetMapping("/employee/newincome")
	public ModelAndView newincome() {
		ModelAndView model = new ModelAndView(VIEWNEWINCOME);
		model.addObject(ConstantsViews.FORMINCOME, new BarDrinkEntity());
		return model;
	}

	@PostMapping("/employee/saveincome")
	public ModelAndView saveincome(@ModelAttribute(ConstantsViews.FORMINCOME) BarDrink income, BindingResult result) {
		ModelAndView model = new ModelAndView();
		incomeValidator.validate(income, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWINCOME);
			model.addObject(ConstantsViews.FORMINCOME, income);
		} else {
			incomeservice.save(mapper.map(income, BarDrinkEntity.class));
			model.addObject(ConstantsViews.DAILY, dailyService.getDailyEmployee());
			model.setViewName("employee/daily/daily");
			model.addObject(ConstantsViews.DATEDAILY, new DateUtil().getNow());
		}
		return model;

	}
}