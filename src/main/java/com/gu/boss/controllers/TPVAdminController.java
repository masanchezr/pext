package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.tpv.TPVService;

@Controller
public class TPVAdminController {

	@Autowired
	private TPVService tpvservice;

	@RequestMapping(value = "/searchtpv")
	public ModelAndView searchtpv() {
		ModelAndView model = new ModelAndView("searchtpv");
		model.addObject("searchForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/resulttpv")
	public ModelAndView resulttpv(@ModelAttribute("searchForm") SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView();
		model.addAllObjects(tpvservice.getOperationsTpv(searchForm.getDatefrom()));
		model.setViewName("resulttpv");
		return model;
	}

}
