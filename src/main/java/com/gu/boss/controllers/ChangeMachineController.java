package com.gu.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.boss.forms.CollectionForm;
import com.gu.forms.SearchByDatesForm;
import com.gu.services.changemachine.ChangeMachineService;
import com.gu.util.constants.ConstantsViews;

@Controller
public class ChangeMachineController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@GetMapping("/searchrecharges")
	public ModelAndView searchRecharges() {
		ModelAndView model = new ModelAndView("boss/recharges/searchrecharges");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		return model;
	}

	@PostMapping("/recharges")
	public ModelAndView recharges(@ModelAttribute(ConstantsViews.FORMSEARCH) SearchByDatesForm searchForm) {
		ModelAndView model = new ModelAndView("boss/recharges/recharges");
		model.addObject("recharges", changeMachineService.recharges(searchForm.getDatefrom()));
		return model;
	}

	@GetMapping("/manualpayments")
	public ModelAndView manualPayments() {
		ModelAndView model = new ModelAndView("boss/manualpayments/selecttake");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		model.addObject("collection", new CollectionForm());
		model.addObject("takings", changeMachineService.getAllTakings());
		return model;
	}

	@PostMapping("/resultManualPayments")
	public ModelAndView resultManualPayments(@ModelAttribute("collection") CollectionForm c) {
		ModelAndView model = new ModelAndView("boss/manualpayments/manualpayments");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		model.addObject("collection", changeMachineService.manualpayments(c.getId()));
		return model;
	}
}
