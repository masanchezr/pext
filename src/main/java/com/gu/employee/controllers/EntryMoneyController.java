package com.gu.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.services.entrymoney.EntryMoneyService;
import com.gu.util.constants.ConstantsJsp;
import com.gu.validators.EntryMoneyValidator;

@Controller
public class EntryMoneyController {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;
	private static final String VIEWNEWENTRYMACHINE = "newentrymachine";

	@RequestMapping(value = "/employee/entrymoney")
	public ModelAndView entrymoney() {
		ModelAndView model = new ModelAndView(ConstantsJsp.VIEWNEWENTRYMONEY);
		model.addObject(ConstantsJsp.FORMENTRYMONEY, new EntryMoneyForm());
		return model;
	}

	@RequestMapping(value = "/employee/entrymoneychangemachine")
	public ModelAndView entryChangeMachine() {
		ModelAndView model = new ModelAndView(VIEWNEWENTRYMACHINE);
		model.addObject(ConstantsJsp.FORMENTRYMONEY, new EntryMoneyForm());
		return model;
	}

	@RequestMapping(value = "/employee/saveEntrymachine")
	public ModelAndView saveEntryMachine(@ModelAttribute(ConstantsJsp.FORMENTRYMONEY) EntryMoneyForm entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMENTRYMONEY, entryMoney);
			model.setViewName(VIEWNEWENTRYMACHINE);
		} else {
			model.addObject(ConstantsJsp.DAILY, entryMoneyService.saveEntryMachine(entryMoney));
			model.setViewName(ConstantsJsp.DAILY);
			model.addObject(ConstantsJsp.DATEDAILY, new Date());
		}
		return model;
	}
}
