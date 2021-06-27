package com.sboot.golden.boss.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.forms.SearchByDatesForm;
import com.sboot.golden.services.income.IncomeService;
import com.sboot.golden.services.incomeluckia.IncomeLuckiaService;
import com.sboot.golden.services.incomemachines.IncomeMachineService;
import com.sboot.golden.services.returnmoneyemployees.ReturnMoneyEmployeeService;
import com.sboot.golden.util.constants.ConstantsViews;

@Controller
public class IncomeBossController {

	@Autowired
	private IncomeService incomeService;

	@Autowired
	private IncomeLuckiaService incomeluckiaservice;

	@Autowired
	private IncomeMachineService incomemachineservice;

	@Autowired
	private ReturnMoneyEmployeeService returnmoneyemployeeservice;

	@GetMapping("/summaryincome")
	public ModelAndView summaryincome() {
		ModelAndView model = new ModelAndView("boss/income/searchincome");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/resultincome")
	public ModelAndView resultincome(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("boss/income/result");
		BigDecimal bardrinks = incomeService.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal luckia = incomeluckiaservice.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal incomemachines = incomemachineservice.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal returns = returnmoneyemployeeservice.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal total = BigDecimal.ZERO;
		if (bardrinks != null) {
			total = total.add(bardrinks);
		}
		if (incomemachines != null) {
			total = total.add(incomemachines);
		}
		if (luckia != null) {
			total = total.add(luckia);
		}
		if (returns != null) {
			total = total.add(returns);
		}
		model.addObject("bardrinks", bardrinks);
		model.addObject("luckia", luckia);
		model.addObject("incomemachines", incomemachines);
		model.addObject("returns", returns);
		model.addObject(ConstantsViews.TOTAL, total);
		return model;
	}
}
