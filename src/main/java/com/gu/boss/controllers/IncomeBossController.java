package com.gu.boss.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.income.IncomeService;
import com.gu.services.incomeluckia.IncomeLuckiaService;
import com.gu.services.incomemachines.IncomeMachineService;
import com.gu.services.returnmoneyemployees.ReturnMoneyEmployeeService;

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

	@RequestMapping(value = "/summaryincome")
	public ModelAndView summaryincome() {
		ModelAndView model = new ModelAndView("summaryincome");
		model.addObject("searchForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/resultincome")
	public ModelAndView resultincome(@ModelAttribute("searchForm") SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("resultincome");
		BigDecimal bardrinks = incomeService.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal luckia = incomeluckiaservice.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal incomemachines = incomemachineservice.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal returns = returnmoneyemployeeservice.findIncomeByMonth(searchForm.getDatefrom());
		BigDecimal total = BigDecimal.ZERO;
		if (bardrinks != null) {
			total.add(bardrinks);
		}
		if (incomemachines != null) {
			total.add(incomemachines);
		}
		if (luckia != null) {
			total.add(luckia);
		}
		if (returns != null) {
			total.add(returns);
		}
		model.addObject("bardrinks", bardrinks);
		model.addObject("luckia", luckia);
		model.addObject("incomemachines", incomemachines);
		model.addObject("returns", returns);
		model.addObject("total", total);
		return model;
	}
}
