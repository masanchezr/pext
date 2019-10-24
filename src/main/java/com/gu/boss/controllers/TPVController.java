package com.gu.boss.controllers;

import java.util.Calendar;
import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.boss.forms.TPV;
import com.gu.boss.validators.TPValidator;
import com.gu.dbaccess.entities.TPVEntity;
import com.gu.forms.SearchByDatesForm;
import com.gu.services.payments.PaymentService;
import com.gu.services.tpv.TPVService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;

@Controller
public class TPVController {

	@Autowired
	private PaymentService paymentservice;

	@Autowired
	private TPVService tpvservice;

	@Autowired
	private TPValidator tpvalidator;

	@Autowired
	private Mapper mapper;

	private static final String VIEWNEWTPV = "boss/tpv/tpv";
	private static final String FORMTPV = "tpv";

	@GetMapping("/searchtpv")
	public ModelAndView searchtpv() {
		ModelAndView model = new ModelAndView("boss/tpv/searchtpv");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/resulttpv")
	public ModelAndView resulttpv(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm) {
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
		model.addObject(ConstantsJsp.PAYMENTS, paymentservice.findAll());
		model.setViewName(VIEWNEWTPV);
		return model;
	}

	@PostMapping("/savetpv")
	public ModelAndView savetpv(@ModelAttribute(FORMTPV) TPV tpv, BindingResult result) {
		ModelAndView model = new ModelAndView();
		tpvalidator.validate(tpv, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWTPV);
			model.addObject(FORMTPV, tpv);
			model.addObject(ConstantsJsp.PAYMENTS, paymentservice.findAll());
		} else {
			// miro si existe ya
			if (tpvservice.exists(tpv.getIdtpv())) {
				model.setViewName(VIEWNEWTPV);
				model.addObject(FORMTPV, tpv);
				result.rejectValue(Constants.IDTPV, "exists");
				model.addObject(ConstantsJsp.PAYMENTS, paymentservice.findAll());
			} else {
				Date datetpv = DateUtil.getDate(tpv.getCreationdate());
				Calendar calendartpv = Calendar.getInstance();
				Calendar now = Calendar.getInstance();
				calendartpv.setTime(datetpv);
				model.addObject(ConstantsJsp.DAILY, tpvservice.save(mapper.map(tpv, TPVEntity.class)));
				if (calendartpv.get(Calendar.YEAR) == now.get(Calendar.YEAR)
						&& calendartpv.get(Calendar.MONTH) == now.get(Calendar.MONTH)
						&& calendartpv.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
					model.setViewName(ConstantsJsp.VIEWDAILYBOSSARROW);
				} else {
					model.setViewName(ConstantsJsp.VIEWDAILYBOSSARROWS);
				}
			}
		}
		return model;
	}
}