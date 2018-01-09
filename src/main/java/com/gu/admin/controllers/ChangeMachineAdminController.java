package com.gu.admin.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.ChangeMachineValidator;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.forms.SearchByDatesForm;
import com.gu.services.awards.AwardService;
import com.gu.services.changemachine.ChangeMachineService;
import com.gu.services.machines.MachineService;
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

	@RequestMapping(value = "/admin/changemachinetotal")
	public ModelAndView changemachinetotal() {
		ModelAndView model = new ModelAndView("changemachinetotal");
		model.addObject("totalmonth", changeMachineService.getIncomeTotalMonth());
		model.addObject("total", changeMachineService.getTotal());
		model.addObject("awards", changeMachineService.getAwards());
		return model;
	}

	@RequestMapping(value = "/admin/resetcm")
	public ModelAndView resetcm() {
		changeMachineService.reset();
		return changemachinetotal();
	}

	@RequestMapping(value = "/admin/ticketsByDay")
	public ModelAndView ticketsByDay(@ModelAttribute("searchform") SearchByDatesForm date) {
		ModelAndView model = new ModelAndView();
		String sfrom = date.getDatefrom();
		model.setViewName("ticketsbyday");
		if (sfrom == null) {
			model.addAllObjects(changeMachineService.ticketsByDay(new Date()));
		} else {
			model.addAllObjects(changeMachineService.ticketsByDay(DateUtil.getDate(sfrom)));
		}
		return model;
	}

	@RequestMapping(value = "/admin/updatechangemachine{id}")
	public ModelAndView updatechangemachine(@PathVariable("id") long id) {
		ModelAndView model = new ModelAndView("updatechangemachine");
		model.addObject("changemachine", changeMachineService.findById(id));
		model.addObject("machines", machineService.searchMachinesOrder());
		model.addObject("awards", awardservice.getAwardsChangeMachine());
		return model;
	}

	@RequestMapping(value = "/admin/savepaymentchangemachine")
	public ModelAndView savepaymentchangemachine(@ModelAttribute("changemachine") ChangeMachineEntity cm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		changeMachineAdminValidator.validate(cm, result);
		if (result.hasErrors()) {
			model.addObject("machines", machineService.searchMachinesOrder());
			model.addObject("awards", awardservice.getAwardsChangeMachine());
			model.addObject("changemachine", cm);
			model.setViewName("updatechangemachine");
		} else {
			ChangeMachineEntity cmentity = changeMachineService.findById(cm.getIdchangemachine());
			if (cmentity != null) {
				model.addObject("machines", machineService.searchMachinesOrder());
				model.addObject("awards", awardservice.getAwardsChangeMachine());
				model.addObject("changemachine", cm);
				model.setViewName("updatechangemachine");
				result.rejectValue("idchangemachine", "selectid");
			} else {
				model.addObject("daily", changeMachineService.save(cm));
				model.setViewName("dailyadmin");
			}
		}
		return model;
	}

	@RequestMapping(value = "/admin/updatepaymentchangemachine")
	public ModelAndView updatepaymentchangemachine(@ModelAttribute("changemachine") ChangeMachineEntity cm,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		changeMachineAdminValidator.validate(cm, result);
		if (result.hasErrors()) {
			model.addObject("machines", machineService.searchMachinesOrder());
			model.addObject("awards", awardservice.getAwardsChangeMachine());
			model.addObject("changemachine", cm);
			model.setViewName("updatechangemachine");
		} else {
			model.addObject("daily", changeMachineService.save(cm));
			model.setViewName("dailyadmin");
		}
		return model;
	}

	@RequestMapping(value = "/admin/lostnumbers")
	public ModelAndView lostnumbers() {
		ModelAndView model = new ModelAndView("lostnumbers");
		model.addObject("lostnumbers", changeMachineService.findLostNumbers());
		return model;
	}
}
