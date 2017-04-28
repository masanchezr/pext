package com.bar.admin.controllers;

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
public class EntryMoneyAdminController {

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;

	@Autowired
	private EntryMoneyService entryMoneyService;

	@RequestMapping(value = "/admin/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("newentrymoneyadmin");
		model.addObject("entrymoneyForm", new EntryMoneyForm());
		model.addObject("origin", Constants.ORIGIN);
		return model;
	}

	@RequestMapping(value = "/admin/saveentrymoney")
	public ModelAndView saveEntryMoney(@ModelAttribute("entrymoneyForm") EntryMoneyForm entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject("entrymoney", entryMoney);
			model.setViewName("newentrymoney");
		} else {
			model.addObject("daily", entryMoneyService.saveEntryMoney(entryMoney));
			model.setViewName("dailyadmin");
			model.addObject("datedaily", new Date());
		}
		return model;
	}
}
