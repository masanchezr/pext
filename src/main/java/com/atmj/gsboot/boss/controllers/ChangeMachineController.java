package com.atmj.gsboot.boss.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.boss.forms.CollectionForm;
import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.changemachine.ChangeMachineService;
import com.atmj.gsboot.services.takings.TakeService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;

import jakarta.validation.Valid;

@Controller
public class ChangeMachineController {

	private ChangeMachineService changeMachineService;

	private TakeService takeService;

	public ChangeMachineController(ChangeMachineService changeMachineService, TakeService takeService) {
		this.changeMachineService = changeMachineService;
		this.takeService = takeService;
	}

	@GetMapping("/searchrecharges")
	public ModelAndView searchRecharges() {
		ModelAndView model = new ModelAndView("boss/recharges/searchrecharges");
		model.addObject(ConstantsViews.MODELCOLLECTION, new CollectionForm());
		model.addObject(Constants.TAKINGS, takeService.getAllTakings());
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
		model.addObject(Constants.TAKINGS, changeMachineService.getAllTakings());
		return model;
	}

	@PostMapping("/resultManualPayments")
	public ModelAndView resultManualPayments(@ModelAttribute(ConstantsViews.MODELCOLLECTION) CollectionForm c) {
		ModelAndView model = new ModelAndView("boss/manualpayments/manualpayments");
		model.addObject(ConstantsViews.FORMSEARCH, new SearchByDatesForm());
		model.addObject(ConstantsViews.MODELCOLLECTION, changeMachineService.manualpayments(c.getId()));
		return model;
	}

	@GetMapping("/reset")
	public ModelAndView reset() {
		ModelAndView model = new ModelAndView("boss/changemachine/reset");
		model.addObject("dateForm", new SearchByDatesForm());
		return model;
	}

	@PostMapping("/resetcm")
	public ModelAndView resetcm(@Valid @ModelAttribute("dateForm") SearchByDatesForm date, BindingResult e) {
		if (e.hasErrors()) {
			ModelAndView model = new ModelAndView("boss/changemachine/reset");
			model.addObject("dateForm", date);
			return model;
		} else {
			changeMachineService.reset(date.getDatefrom());
			return changemachinetotal();
		}
	}

	@GetMapping("/changemachinetotal")
	public ModelAndView changemachinetotal() {
		ModelAndView model = new ModelAndView("boss/changemachine/changemachinetotal");
		model.addObject("totalmonth", changeMachineService.getIncomeTotalMonth());
		model.addObject(ConstantsViews.TOTAL, changeMachineService.getTotal());
		model.addObject(ConstantsViews.AWARDS, changeMachineService.getAwards());
		return model;
	}
}
