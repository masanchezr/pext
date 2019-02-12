package com.gu.boss.controllers;

import java.util.Date;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	private TPVService tpvservice;

	@Autowired
	private PaymentService paymentservice;

	@Autowired
	private TPValidator tpvalidator;

	@Autowired
	private Mapper mapper;

	private static final String VIEWNEWTPV = "newtpv";
	private static final String FORMTPV = "tpv";

	@RequestMapping(value = "/searchtpv")
	public ModelAndView searchtpv() {
		ModelAndView model = new ModelAndView("searchtpv");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/resulttpv")
	public ModelAndView resulttpv(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView();
		Date from = DateUtil.getDayMonthMinimum(searchForm.getDatefrom());
		Date until = DateUtil.getDayMonthMaximum(searchForm.getDateuntil());
		model.addObject("amount", tpvservice.sumByCreationdate(from, until));
		model.addObject(tpvservice.getOperationsTpv(from, until));
		model.setViewName("resulttpv");
		return model;
	}

	@RequestMapping(value = "/newtpv")
	public ModelAndView newtpv() {
		ModelAndView model = new ModelAndView();
		model.addObject(FORMTPV, new TPV());
		model.addObject("payments", paymentservice.findAll());
		model.setViewName(VIEWNEWTPV);
		return model;
	}

	@RequestMapping(value = "/savetpv")
	public ModelAndView savetpv(@ModelAttribute(FORMTPV) TPV tpv, BindingResult result) {
		ModelAndView model = new ModelAndView();
		tpvalidator.validate(tpv, result);
		if (result.hasErrors()) {
			model.setViewName(VIEWNEWTPV);
			model.addObject(FORMTPV, tpv);
		} else {
			// miro si existe ya
			if (tpvservice.exists(tpv.getIdtpv())) {
				model.setViewName(VIEWNEWTPV);
				model.addObject(FORMTPV, tpv);
				result.rejectValue(Constants.IDTPV, "exists");
			} else {
				model.addObject(ConstantsJsp.DAILY, tpvservice.save(mapper.map(tpv, TPVEntity.class)));
				model.setViewName(ConstantsJsp.VIEWDAILYBOSSARROW);
			}
		}
		return model;
	}

}
