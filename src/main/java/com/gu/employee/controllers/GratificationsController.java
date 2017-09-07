package com.gu.employee.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.services.gratifications.GratificationService;
import com.gu.services.machines.MachineService;

@Controller
public class GratificationsController {

	@Autowired
	private GratificationService gratificationservice;

	@Autowired
	private MachineService machineservice;

	@RequestMapping(value = "/employee/newgratification")
	public ModelAndView newgratification() {
		ModelAndView model = new ModelAndView("newgratification");
		model.addObject("machines", machineservice.searchAllMachinesOrder());
		model.addObject("gratification", new GratificationEntity());
		return model;
	}

	@RequestMapping(value = "/employee/savegratification")
	public ModelAndView save(@ModelAttribute("gratification") GratificationEntity g, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idgratification", "selectgratification");
		if (arg1.hasErrors()) {
			model.addObject("machines", machineservice.searchAllMachinesOrder());
			model.addObject("gratification", g);
			model.setViewName("newgratification");
		} else if (g.getMachine().getIdmachine().equals(0L)) {
			model.addObject("machines", machineservice.searchAllMachinesOrder());
			model.addObject("gratification", g);
			model.setViewName("newgratification");
			arg1.rejectValue("idgratification", "selectmachine");
		} else {
			GratificationEntity gratification = gratificationservice.searchGratificationActive(g.getIdgratification());
			if (gratification == null) {
				model.addObject("machines", machineservice.searchAllMachinesOrder());
				model.addObject("gratification", g);
				model.setViewName("newgratification");
				arg1.rejectValue("idgratification", "gratificationsproblem");
			} else {
				gratification.setMachine(g.getMachine());
				model.addObject("daily", gratificationservice.save(gratification));
				model.setViewName("daily");
			}
		}
		return model;

	}

	@RequestMapping(value = "/employee/registernumber")
	public ModelAndView registerNumber() {
		ModelAndView model = new ModelAndView("registergratification");
		model.addObject("gratification", new GratificationEntity());
		return model;
	}

	@RequestMapping(value = "/employee/registergratification")
	public ModelAndView registerGratification(GratificationEntity g, BindingResult arg1) {
		ModelAndView model = new ModelAndView("successemployee");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idgratification", "selectgratification");
		if (arg1.hasErrors()) {
			model.addObject("gratification", g);
			model.setViewName("registergratification");
		} else if (!gratificationservice.exists(g.getIdgratification())) {
			gratificationservice.registerNumberGratification(g);
		} else {
			model.addObject("gratification", g);
			model.setViewName("registergratification");
			arg1.reject("idgratification", "exists");
		}
		return model;
	}

	@RequestMapping(value = "/employee/lastgratifications")
	public ModelAndView lastgratifications() {
		ModelAndView model = new ModelAndView("lastgratifications");
		model.addObject("gratifications", gratificationservice.lastNumGratifications());
		return model;
	}
}
