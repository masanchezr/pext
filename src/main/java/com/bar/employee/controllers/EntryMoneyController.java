package com.bar.employee.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bar.admin.forms.EntryMoneyForm;
import com.bar.services.entrymoney.EntryMoneyService;
import com.bar.util.constants.Constants;
import com.bar.validators.EntryMoneyValidator;

@Controller
public class EntryMoneyController {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;

	@RequestMapping(value = "/employee/entrymoney")
	public ModelAndView entrymoney() {
		ModelAndView model = new ModelAndView("newentrymoney");
		model.addObject("entrymoneyForm", new EntryMoneyForm());
		return model;
	}

	@RequestMapping(value = "/employee/saveentrymoney")
	public ModelAndView saveEntryMoney(@ModelAttribute("entrymoneyForm") EntryMoneyForm entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject("entrymoney", entryMoney);
			model.setViewName("newentrymoney");
		} else {
			entryMoney.setOrigin(Constants.STRINGPROVIDING);
			model.addObject("daily", entryMoneyService.saveEntryMoneyEmployee(entryMoney));
			model.setViewName("daily");
			model.addObject("datedaily", new Date());
		}
		return model;
	}

	@RequestMapping(value = "/employee/entrymoneychangemachine")
	public ModelAndView entryChangeMachine() {
		ModelAndView model = new ModelAndView("newentrymachine");
		model.addObject("entrymoneyForm", new EntryMoneyForm());
		return model;
	}
}
