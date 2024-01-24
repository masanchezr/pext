package com.atmj.gsboot.boss.controllers;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.boss.forms.CollectionForm;
import com.atmj.gsboot.services.income.IncomeService;
import com.atmj.gsboot.services.incomemachines.IncomeMachineService;
import com.atmj.gsboot.services.incomesportsbets.IncomeSportsBetsService;
import com.atmj.gsboot.services.returnmoneyemployees.ReturnMoneyEmployeeService;
import com.atmj.gsboot.services.takings.TakeService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class IncomeBossController {

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private IncomeSportsBetsService incomesportsbetsservice;

	@Autowired
	private IncomeMachineService incomemachineservice;

	@Autowired
	private ReturnMoneyEmployeeService returnmoneyemployeeservice;

	@Autowired
	private TakeService takeService;

	@GetMapping("/summaryincome")
	public ModelAndView summaryincome() {
		ModelAndView model = new ModelAndView("boss/income/searchincome");
		model.addObject(ConstantsViews.MODELCOLLECTION, new CollectionForm());
		model.addObject("takings", takeService.getAllTakings());
		return model;
	}

	@PostMapping("/resultincome")
	public ModelAndView resultincome(@ModelAttribute(ConstantsViews.MODELCOLLECTION) CollectionForm c) {
		ModelAndView model = new ModelAndView("boss/income/result");
		Long id = c.getId();
		Date from = takeService.getFrom(id);
		Date until = takeService.findById(id).getTakedate();
		BigDecimal bardrinks = incomeService.findIncomeByMonth(from, until);
		BigDecimal sportsbets = incomesportsbetsservice.findIncomeByMonth(from, until);
		BigDecimal incomemachines = incomemachineservice.findIncomeByMonth(from, until);
		BigDecimal returns = returnmoneyemployeeservice.findIncomeByMonth(from, until);
		BigDecimal total = BigDecimal.ZERO;
		if (bardrinks != null) {
			total = total.add(bardrinks);
		}
		if (incomemachines != null) {
			total = total.add(incomemachines);
		}
		if (sportsbets != null) {
			total = total.add(sportsbets);
		}
		if (returns != null) {
			total = total.add(returns);
		}
		model.addObject("bardrinks", bardrinks);
		model.addObject("sportsbets", sportsbets);
		model.addObject("incomemachines", incomemachines);
		model.addObject("returns", returns);
		model.addObject(ConstantsViews.TOTAL, total);
		return model;
	}
}
