package com.sboot.golden.boss.controllers;

import java.util.Calendar;
import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.boss.forms.TPV;
import com.sboot.golden.boss.validators.TPValidator;
import com.sboot.golden.dbaccess.entities.TPVEntity;
import com.sboot.golden.forms.SearchByDatesForm;
import com.sboot.golden.services.changemachine.ChangeMachineService;
import com.sboot.golden.services.payments.PaymentService;
import com.sboot.golden.services.tpv.TPVService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;
import com.sboot.golden.util.date.DateUtil;

@Controller
public class TPVController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private PaymentService paymentservice;

	@Autowired
	private TPVService tpvservice;

	@Autowired
	private Mapper mapper;

	private static final String VIEWNEWTPV = "boss/tpv/tpv";
	private static final String FORMTPV = "tpv";

	@GetMapping("/searchtpv")
	public ModelAndView searchtpv() {
		ModelAndView model = new ModelAndView("boss/tpv/searchtpv");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/resulttpv")
	public ModelAndView resulttpv(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView();
		Date from = DateUtil.getDayMonthMinimum(searchForm.getDatefrom());
		Date until = DateUtil.getDayMonthMaximum(searchForm.getDatefrom());
		model.addObject("amount", tpvservice.sumByCreationdate(from, until));
		model.addObject(tpvservice.getOperationsTpv(from, until));
		model.setViewName("boss/tpv/resulttpv");
		return model;
	}

	@GetMapping("/newtpv")
	public ModelAndView newtpv() {
		ModelAndView model = new ModelAndView();
		model.addObject(FORMTPV, new TPV());
		model.addObject(ConstantsViews.PAYMENTS, paymentservice.findAll());
		model.setViewName(VIEWNEWTPV);
		return model;
	}

	@PostMapping("/savetpv")
	public ModelAndView savetpv(@Validated(TPValidator.class) @ModelAttribute(FORMTPV) TPV tpv, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWTPV);
			model.addObject(FORMTPV, tpv);
			model.addObject(ConstantsViews.PAYMENTS, paymentservice.findAll());
		} else {
			// miro si existe ya
			if (tpvservice.exists(tpv.getIdtpv())) {
				model.setViewName(VIEWNEWTPV);
				model.addObject(FORMTPV, tpv);
				result.rejectValue(Constants.IDTPV, "exists");
				model.addObject(ConstantsViews.PAYMENTS, paymentservice.findAll());
			} else {
				Date datetpv = DateUtil.getDate(tpv.getCreationdate());
				Calendar calendartpv = Calendar.getInstance();
				Calendar now = Calendar.getInstance();
				calendartpv.setTime(datetpv);
				model.addObject(ConstantsViews.DAILY, tpvservice.save(mapper.map(tpv, TPVEntity.class)));
				changeMachineService.subtractChangeMachineTotal("127.0.0.1", tpv.getAmount());
				if (calendartpv.get(Calendar.YEAR) == now.get(Calendar.YEAR)
						&& calendartpv.get(Calendar.MONTH) == now.get(Calendar.MONTH)
						&& calendartpv.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
					model.setViewName(ConstantsViews.VIEWDAILYBOSSARROW);
				} else {
					model.setViewName(ConstantsViews.VIEWDAILYBOSSARROWS);
				}
				model.addObject(ConstantsViews.DATEDAILY, DateUtil.getStringDateFormatddMMyyyy(datetpv));
			}
		}
		return model;
	}
}