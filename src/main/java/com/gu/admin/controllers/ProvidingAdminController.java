package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.admin.validators.ProvidingValidator;
import com.gu.dbaccess.entities.ProvidingEntity;
import com.gu.services.entrymoney.EntryMoneyService;

@Controller
public class ProvidingAdminController {

	@Autowired
	private EntryMoneyService providingService;

	@Autowired
	private ProvidingValidator providingValidator;

	@RequestMapping(value = "/admin/newproviding")
	public ModelAndView newproviding() {
		ModelAndView model = new ModelAndView("newproviding");
		model.addObject("providing", new ProvidingEntity());
		return model;
	}

	@RequestMapping(value = "/admin/providing")
	public ModelAndView providing(@ModelAttribute("providing") ProvidingEntity providing, BindingResult result) {
		ModelAndView model = new ModelAndView();
		providingValidator.validate(providing, result);
		if (result.hasErrors()) {
			model.setViewName("newproviding");
			model.addObject("providing", providing);
		} else {
			providingService.save(providing);
			model.setViewName("successadmin");
		}
		return model;
	}

	@RequestMapping(value = "/admin/providingtotal")
	public ModelAndView providingtotal() {
		ModelAndView model = new ModelAndView("providingtotal");
		model.addObject("totalamount", providingService.searchTotalProviding());
		return model;
	}
}
