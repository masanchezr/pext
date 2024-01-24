package com.atmj.gsboot.boss.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.boss.forms.CollectionForm;
import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.changemachine.ChangeMachineService;
import com.atmj.gsboot.services.takings.TakeService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class ChangeMachineController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private TakeService takeService;

	@GetMapping("/searchrecharges")
	public ModelAndView searchRecharges() {
		ModelAndView model = new ModelAndView("boss/recharges/searchrecharges");
		model.addObject(ConstantsViews.MODELCOLLECTION, new CollectionForm());
		model.addObject("takings", takeService.getAllTakings());
		return model;
	}

	@PostMapping("/recharges")
	public ModelAndView recharges(@ModelAttribute(ConstantsViews.MODELCOLLECTION) CollectionForm c) {
		ModelAndView model = new ModelAndView("boss/recharges/recharges");
		Long id = c.getId();
		model.addObject("recharges",
				changeMachineService.recharges(takeService.getFrom(id), takeService.findById(id).getTakedate()));
		return model;
	}

	@GetMapping("/manualpayments")
	public ModelAndView manualPayments() {
		ModelAndView model = new ModelAndView("boss/manualpayments/selecttake");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		model.addObject(ConstantsViews.MODELCOLLECTION, new CollectionForm());
		model.addObject("takings", changeMachineService.getAllTakings());
		return model;
	}

	@PostMapping("/resultManualPayments")
	public ModelAndView resultManualPayments(@ModelAttribute(ConstantsViews.MODELCOLLECTION) CollectionForm c) {
		ModelAndView model = new ModelAndView("boss/manualpayments/manualpayments");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		model.addObject(ConstantsViews.MODELCOLLECTION, changeMachineService.manualpayments(c.getId()));
		return model;
	}
}
