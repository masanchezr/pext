package com.atmj.gsboot.employee.controllers;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.BarDrinkEntity;
import com.atmj.gsboot.employee.forms.BarDrink;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.services.income.IncomeService;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

@Controller
public class IncomeController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private IncomeService incomeservice;

	@Autowired
	private ModelMapper mapper;

	private static final String VIEWNEWINCOME = "employee/income/newincome";

	@GetMapping("/employee/newincome")
	public ModelAndView newincome() {
		ModelAndView model = new ModelAndView(VIEWNEWINCOME);
		model.addObject(ConstantsViews.FORMINCOME, new BarDrinkEntity());
		return model;
	}

	@PostMapping("/employee/saveincome")
	public ModelAndView saveincome(@Valid BarDrink income, BindingResult result) {
		ModelAndView model = new ModelAndView();
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