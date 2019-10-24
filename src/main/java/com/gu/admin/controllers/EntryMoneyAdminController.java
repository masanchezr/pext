package com.gu.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.services.dailies.DailyService;
import com.gu.services.entrymoney.EntryMoneyService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;
import com.gu.validators.EntryMoneyValidator;

@Controller
public class EntryMoneyAdminController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Autowired
	private EntryMoneyValidator entryMoneyValidator;

	@GetMapping("/admin/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("admin/newentrymoney");
		model.addObject(ConstantsJsp.FORMENTRYMONEY, new EntryMoneyForm());
		model.addObject("origin", Constants.getOrigin());
		return model;
	}

	@PostMapping("/admin/saveentrymoney")
	public ModelAndView saveEntryMoney(@ModelAttribute(ConstantsJsp.FORMENTRYMONEY) EntryMoneyForm entryMoney,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		entryMoneyValidator.validate(entryMoney, result);
		if (result.hasErrors()) {
			model.addObject(ConstantsJsp.FORMENTRYMONEY, entryMoney);
			model.setViewName("admin/newentrymoney");
		} else {
			entryMoneyService.saveEntryMoney(entryMoney);
			model.addObject(ConstantsJsp.DAILY, dailyService.getDaily(DateUtil.getDateFormatddMMyyyy(new Date())));
			model.setViewName(ConstantsJsp.VIEWDAILYADMINARROW);
			model.addObject(ConstantsJsp.DATEDAILY, DateUtil.getStringDateFormatddMMyyyy(new Date()));
		}
		return model;
	}
}
