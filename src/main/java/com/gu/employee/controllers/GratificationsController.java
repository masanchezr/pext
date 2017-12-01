package com.gu.employee.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.services.gratifications.GratificationService;
import com.gu.services.machines.MachineService;
import com.gu.util.constants.Constants;

@Controller
public class GratificationsController {

	@Autowired
	private GratificationService gratificationservice;

	@Autowired
	private MachineService machineservice;

	@RequestMapping(value = "/employee/newgratification")
	public ModelAndView newgratification() {
		ModelAndView model = new ModelAndView("newgratification");
		model.addObject("machines", machineservice.searchMachinesOrder());
		model.addObject("gratification", new GratificationEntity());
		return model;
	}

	/**
	 * pagamos propina
	 * 
	 * @param g
	 * @param arg1
	 * @return
	 */
	@RequestMapping(value = "/employee/savegratification")
	public void save(@ModelAttribute("gratification") GratificationEntity g, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "idgratification", "selectgratification");
		if (arg1.hasErrors()) {
			model.addObject("machines", machineservice.searchMachinesOrder());
			model.addObject("gratification", g);
			model.setViewName("newgratification");
		} else {
			GratificationEntity gratification = gratificationservice.searchGratificationActive(g.getIdgratification());
			if (gratification == null) {
				arg1.rejectValue("idgratification", "gratificationsproblem");
				model.addObject("machines", machineservice.searchMachinesOrder());
				model.addObject("gratification", g);
				model.setViewName("newgratification");
			} else {
				gratification.setMachine(g.getMachine());
				model.addObject("daily", gratificationservice.save(gratification, user));
				model.setViewName("daily");
			}
		}
		// return model;
	}

	@RequestMapping(value = "/employee/registernumber")
	public ModelAndView registerNumber() {
		ModelAndView model = new ModelAndView("registergratification");
		model.addObject("gratification", new GratificationEntity());
		return model;
	}

	@RequestMapping(value = "/employee/registergratification")
	public ModelAndView registerGratification(@ModelAttribute("gratification") GratificationEntity g,
			BindingResult arg1, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("successemployee");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "client", "client");
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
		if (arg1.hasErrors()) {
			model.addObject("gratification", g);
			model.setViewName("registergratification");
		} else {
			gratificationservice.registerNumberGratification(g, user, path);
			File file = new File(path.concat("ticket.pdf"));
			InputStream inputStream;
			try {
				inputStream = new FileInputStream(file);
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition", "attachment; filename=ticket.pdf");
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
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
