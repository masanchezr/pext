package com.atmj.gsboot.admin.controllers;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.admin.forms.ChangeMachine;
import com.atmj.gsboot.admin.forms.EntryMoneyForm;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineTotalEntity;
import com.atmj.gsboot.forms.SearchByDatesForm;
import com.atmj.gsboot.services.awards.AwardService;
import com.atmj.gsboot.services.changemachine.ChangeMachineService;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.services.machines.MachineService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class ChangeMachineAdminController {

	@Autowired
	private AwardService awardservice;

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private DailyService dailyService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private ModelMapper mapper;

	private static final String CHANGEMACHINE = "changemachine";
	private static final String UPDATEVIEW = "admin/changemachine/updatechangemachine";

	@GetMapping("/admin/changemachinetotal")
	public ModelAndView changemachinetotal() {
		ModelAndView model = new ModelAndView("admin/changemachine/changemachinetotal");
		model.addObject("totalmonth", changeMachineService.getIncomeTotalMonth());
		model.addObject(ConstantsViews.TOTAL, changeMachineService.getTotal());
		model.addObject(ConstantsViews.AWARDS, changeMachineService.getAwards());
		return model;
	}

	@GetMapping("/admin/newentryvisible")
	public ModelAndView newentryvisible() {
		ModelAndView model = new ModelAndView("admin/changemachine/newentrydeposittovisible");
		model.addObject("moneyForm", new EntryMoneyForm());
		return model;

	}

	@PostMapping("/admin/saveentryvisible")
	public ModelAndView saveentryvisible(@ModelAttribute("moneyForm") EntryMoneyForm entryForm, BindingResult result) {
		ChangeMachineTotalEntity total = changeMachineService.getTotal();
		if (total.getDeposit().compareTo(entryForm.getAmount()) < 0) {
			ModelAndView model = new ModelAndView("admin/changemachine/newentrydeposittovisible");
			model.addObject("money", new EntryMoneyForm());
			result.rejectValue("amount", "amountplusthan");
			return model;
		} else {
			changeMachineService.entryToVisible(entryForm.getAmount());
			return changemachinetotal();
		}
	}

	@PostMapping("/admin/resetcm")
	public ModelAndView resetcm(@ModelAttribute("dateForm") SearchByDatesForm date) {
		changeMachineService.reset(date.getDatefrom());
		return changemachinetotal();
	}

	@GetMapping("/admin/reset")
	public ModelAndView reset() {
		ModelAndView model = new ModelAndView("admin/changemachine/reset");
		model.addObject("dateForm", new SearchByDatesForm());
		return model;
	}

	@GetMapping("/admin/updatechangemachine{id}")
	public ModelAndView updatechangemachine(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView(UPDATEVIEW);
		model.addObject(CHANGEMACHINE, changeMachineService.findById(id));
		model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
		model.addObject(ConstantsViews.AWARDS, awardservice.getAwardsChangeMachine());
		return model;
	}

	@PostMapping("/admin/savepaymentchangemachine")
	public ModelAndView savepaymentchangemachine(@Valid ChangeMachine cm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsViews.AWARDS, awardservice.getAwardsChangeMachine());
			model.addObject(CHANGEMACHINE, cm);
			model.setViewName(UPDATEVIEW);
		} else {
			ChangeMachineEntity cmentity = changeMachineService.findById(cm.getIdchangemachine());
			if (cmentity != null) {
				model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
				model.addObject(ConstantsViews.AWARDS, awardservice.getAwardsChangeMachine());
				model.addObject(CHANGEMACHINE, cm);
				model.setViewName(UPDATEVIEW);
				result.rejectValue(Constants.IDCHANGEMACHINE, ConstantsViews.ERRORSELECTID);
			} else {
				model.addObject(ConstantsViews.DAILY, dailyService.save(mapper.map(cm, ChangeMachineEntity.class)));
				model.setViewName(ConstantsViews.VIEWDAILYADMINARROWS);
			}
		}
		return model;
	}

	@PostMapping("/admin/updatepaymentchangemachine")
	public ModelAndView updatepaymentchangemachine(@Valid ChangeMachine cm, BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsViews.AWARDS, awardservice.getAwardsChangeMachine());
			model.addObject(CHANGEMACHINE, cm);
			model.setViewName(UPDATEVIEW);
		} else {
			model.addObject(ConstantsViews.DAILY, dailyService.save(mapper.map(cm, ChangeMachineEntity.class)));
			model.setViewName(ConstantsViews.VIEWDAILYADMINARROWS);
		}
		return model;
	}
}
