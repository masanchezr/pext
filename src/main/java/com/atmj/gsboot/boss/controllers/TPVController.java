package com.atmj.gsboot.boss.controllers;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.boss.forms.CollectionForm;
import com.atmj.gsboot.boss.forms.TPV;
import com.atmj.gsboot.converters.TPVConverter;
import com.atmj.gsboot.services.changemachine.ChangeMachineService;
import com.atmj.gsboot.services.payments.PaymentService;
import com.atmj.gsboot.services.takings.TakeService;
import com.atmj.gsboot.services.tpv.TPVService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

import jakarta.validation.Valid;

@Controller
public class TPVController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private PaymentService paymentservice;

	@Autowired
	private TakeService takeService;

	@Autowired
	private TPVService tpvservice;

	@Autowired
	private TPVConverter mapper;

	private static final String VIEWNEWTPV = "boss/tpv/tpv";
	private static final String FORMTPV = "tpv";

	@GetMapping("/searchtpv")
	public ModelAndView searchtpv() {
		ModelAndView model = new ModelAndView("boss/tpv/searchtpv");
		model.addObject(ConstantsViews.MODELCOLLECTION, new CollectionForm());
		model.addObject("takings", takeService.getAllTakings());
		return model;
	}

	@PostMapping("/resulttpv")
	public ModelAndView resulttpv(@ModelAttribute(ConstantsViews.MODELCOLLECTION) CollectionForm c) {
		ModelAndView model = new ModelAndView();
		Long id = c.getId();
		Date from = takeService.getFrom(id);
		Date until = takeService.findById(id).getTakedate();
		model.addObject("amount", tpvservice.sumByCreationdate(from, until));
		model.addObject("operations", tpvservice.getOperationsTpv(from, until));
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
	public ModelAndView savetpv(@Valid TPV tpv, BindingResult result) {
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
				Date datetpv = DateUtil.getDate(tpv.getSdate());
				Calendar calendartpv = Calendar.getInstance();
				Calendar now = Calendar.getInstance();
				calendartpv.setTime(datetpv);
				model.addObject(ConstantsViews.DAILY, tpvservice.save(mapper.convertToEntity(tpv)));
				changeMachineService.subtractChangeMachineTotal("127.0.0.1", tpv.getAmount());
				if (calendartpv.get(Calendar.YEAR) == now.get(Calendar.YEAR)
						&& calendartpv.get(Calendar.MONTH) == now.get(Calendar.MONTH)
						&& calendartpv.get(Calendar.DAY_OF_MONTH) == now.get(Calendar.DAY_OF_MONTH)) {
					model.setViewName(ConstantsViews.VIEWDAILYBOSSARROW);
				} else {
					model.setViewName(ConstantsViews.VIEWDAILYBOSSARROWS);
				}
			}
		}
		return model;
	}
}