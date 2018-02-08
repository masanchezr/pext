package com.gu.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.services.entrymoney.EntryMoneyService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.validators.EntryMoneyValidator;

@Controller
public class EntryMoneyAdminController {

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;

	@Autowired
	private EntryMoneyService entryMoneyService;

	@RequestMapping(value = "/admin/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("newentrymoneyadmin");
		model.addObject("ConstantsJsp.FORMENTRYMONEY", new EntryMoneyForm());
		model.addObject("origin", Constants.getOrigin());
		return model;
	}

	@RequestMapping(value = "/admin/saveentrymoney")
	public ModelAndView saveEntryMoney(@ModelAttribute("ConstantsJsp.FORMENTRYMONEY") EntryMoneyForm entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMENTRYMONEY, entryMoney);
			model.setViewName(ConstantsJsp.VIEWNEWENTRYMONEY);
		} else {
			model.addObject(ConstantsJsp.DAILY, entryMoneyService.saveEntryMoney(entryMoney));
			model.setViewName("dailyadminarrow");
			model.addObject(ConstantsJsp.DATEDAILY, new Date());
		}
		return model;
	}
}
