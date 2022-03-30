package com.sboot.golden.admin.controllers;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.admin.forms.EntryMoneyForm;
import com.sboot.golden.services.dailies.DailyService;
import com.sboot.golden.services.entrymoney.EntryMoneyService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

@Controller
public class EntryMoneyAdminController {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private EntryMoneyService entryMoneyService;

	@GetMapping("/admin/newentrymoney")
	public ModelAndView newentrymoney() {
		ModelAndView model = new ModelAndView("admin/newentrymoney");
		model.addObject(ConstantsViews.FORMENTRYMONEY, new EntryMoneyForm());
		model.addObject("origin", Constants.getOrigin());
		return model;
	}

	@PostMapping("/admin/saveentrymoney")
	public ModelAndView saveEntryMoney(@Valid EntryMoneyForm entryMoney, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(ConstantsViews.FORMENTRYMONEY, entryMoney);
			model.setViewName("admin/newentrymoney");
		} else {
			entryMoneyService.saveEntryMoney(entryMoney);
			model.addObject(ConstantsViews.DAILY, dailyService.getDaily(DateUtil.getDateFormatddMMyyyy(new Date())));
			model.setViewName(ConstantsViews.VIEWDAILYADMINARROW);
			model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateFormatddMMyyyy(new Date()));
		}
		return model;
	}
}
