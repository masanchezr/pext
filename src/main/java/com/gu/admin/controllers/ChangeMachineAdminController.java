package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.admin.validators.ChangeMachineValidator;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.forms.SearchByDatesForm;
import com.gu.services.awards.AwardService;
import com.gu.services.changemachine.ChangeMachineService;
import com.gu.services.machines.MachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;
import com.gu.util.date.DateUtil;

@Controller
public class ChangeMachineAdminController {

	@Autowired
	private ChangeMachineService changeMachineService;

	@Autowired
	private MachineService machineService;

	@Autowired
	private AwardService awardservice;

	@Autowired
	private ChangeMachineValidator changeMachineAdminValidator;

	private static final String CHANGEMACHINE = "changemachine";
	private static final String UPDATEVIEW = "updatechangemachine";

	@RequestMapping(value = "/admin/changemachinetotal")
	public ModelAndView changemachinetotal() {
		ModelAndView model = new ModelAndView("changemachinetotal");
		model.addObject("totalmonth", changeMachineService.getIncomeTotalMonth());
		model.addObject(ConstantsJsp.TOTAL, changeMachineService.getTotal());
		model.addObject(ConstantsJsp.AWARDS, changeMachineService.getAwards());
		return model;
	}

	@RequestMapping(value = "/admin/newentryvisible")
	public ModelAndView newentryvisible() {
		ModelAndView model = new ModelAndView("newentrydeposittovisible");
		model.addObject("moneyForm", new EntryMoneyForm());
		return model;

	}

	@RequestMapping(value = "/admin/saveentryvisible")
	public ModelAndView saveentryvisible(@ModelAttribute("moneyForm") EntryMoneyForm entryForm, BindingResult result) {
		ChangeMachineTotalEntity total = changeMachineService.getTotal();
		if (total.getDeposit().compareTo(entryForm.getAmount()) < 0) {
			ModelAndView model = new ModelAndView("newentrydeposittovisible");
			model.addObject("money", new EntryMoneyForm());
			result.rejectValue("amount", "amountplusthan");
			return model;
		} else {
			changeMachineService.entryToVisible(entryForm.getAmount());
			return changemachinetotal();
		}
	}

	@RequestMapping(value = "/admin/resetcm")
	public ModelAndView resetcm(@ModelAttribute("dateForm") SearchByDatesForm date) {
		changeMachineService.reset(date.getDatefrom());
		return changemachinetotal();
	}

	@RequestMapping(value = "/admin/reset")
	public ModelAndView reset() {
		ModelAndView model = new ModelAndView("resetcm");
		model.addObject("dateForm", new SearchByDatesForm());
		return model;
	}

	@RequestMapping(value = "/admin/ticketsByDay")
	public ModelAndView ticketsByDay(@ModelAttribute("searchform") SearchByDatesForm date) {
		ModelAndView model = new ModelAndView();
		String sfrom = date.getDatefrom();
		model.setViewName("ticketsbyday");
		if (sfrom == null) {
			model.addAllObjects(changeMachineService.ticketsByDay(new DateUtil().getNow()));
		} else {
			model.addAllObjects(changeMachineService.ticketsByDay(DateUtil.getDate(sfrom)));
		}
		return model;
	}

	@RequestMapping(value = "/admin/updatechangemachine{id}")
	public ModelAndView updatechangemachine(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView(UPDATEVIEW);
		model.addObject(CHANGEMACHINE, changeMachineService.findById(id));
		model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
		model.addObject(ConstantsJsp.AWARDS, awardservice.getAwardsChangeMachine());
		return model;
	}

	@RequestMapping(value = "/admin/savepaymentchangemachine")
	public ModelAndView savepaymentchangemachine(@ModelAttribute(CHANGEMACHINE) ChangeMachineEntity cm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		changeMachineAdminValidator.validate(cm, result);
		if (result.hasErrors()) {
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsJsp.AWARDS, awardservice.getAwardsChangeMachine());
			model.addObject(CHANGEMACHINE, cm);
			model.setViewName(UPDATEVIEW);
		} else {
			ChangeMachineEntity cmentity = changeMachineService.findById(cm.getIdchangemachine());
			if (cmentity != null) {
				model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
				model.addObject(ConstantsJsp.AWARDS, awardservice.getAwardsChangeMachine());
				model.addObject(CHANGEMACHINE, cm);
				model.setViewName(UPDATEVIEW);
				result.rejectValue(Constants.IDCHANGEMACHINE, ConstantsJsp.ERRORSELECTID);
			} else {
				model.addObject(ConstantsJsp.DAILY, changeMachineService.save(cm));
				model.setViewName("dailyadminarrows");
			}
		}
		return model;
	}

	@RequestMapping(value = "/admin/updatepaymentchangemachine")
	public ModelAndView updatepaymentchangemachine(@ModelAttribute(CHANGEMACHINE) ChangeMachineEntity cm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		changeMachineAdminValidator.validate(cm, result);
		if (result.hasErrors()) {
			model.addObject(Constants.MACHINES, machineService.searchMachinesOrder());
			model.addObject(ConstantsJsp.AWARDS, awardservice.getAwardsChangeMachine());
			model.addObject(CHANGEMACHINE, cm);
			model.setViewName(UPDATEVIEW);
		} else {
			model.addObject(ConstantsJsp.DAILY, changeMachineService.save(cm));
			model.setViewName("dailyadminarrows");
		}
		return model;
	}
}
