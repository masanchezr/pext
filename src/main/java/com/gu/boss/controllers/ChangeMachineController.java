package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.forms.SearchByDatesForm;
import com.gu.services.changemachine.ChangeMachineService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class ChangeMachineController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@RequestMapping(value = "/searchrecharges")
	public ModelAndView searchRecharges() {
		ModelAndView model = new ModelAndView("searchrecharges");
		model.addObject(ConstantsJsp.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/recharges")
	public ModelAndView recharges(@ModelAttribute(ConstantsJsp.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("recharges");
		model.addObject("recharges", changeMachineService.recharges(searchForm.getDatefrom()));
		return model;
	}

}
