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
import com.gu.employee.validators.GratificationsValidator;
import com.gu.services.gratifications.GratificationService;
import com.gu.services.machines.MachineService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class GratificationsController {

	@Autowired
	private GratificationService gratificationservice;

	@Autowired
	private MachineService machineservice;

	@Autowired
	private GratificationsValidator gvalidator;

	private static final String GRATIFICATION = "gratification";
	private static final String VIEWNEWGRATIFICATION = "newgratification";
	private static final String VIEWREGISTERGRATIFICATION = "registergratification";
	private static final String IDGRATIFICATION = "idgratification";

	@RequestMapping(value = "/employee/newgratification")
	public ModelAndView newgratification() {
		ModelAndView model = new ModelAndView(VIEWNEWGRATIFICATION);
		model.addObject(Constants.MACHINES, machineservice.searchMachinesOrder());
		model.addObject(GRATIFICATION, new GratificationEntity());
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
	public ModelAndView save(@ModelAttribute(GRATIFICATION) GratificationEntity g, BindingResult arg1) {
		ModelAndView model = new ModelAndView();
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, IDGRATIFICATION, "selectgratification");
		if (arg1.hasErrors()) {
			model.addObject(Constants.MACHINES, machineservice.searchMachinesOrder());
			model.addObject(GRATIFICATION, g);
			model.setViewName(VIEWNEWGRATIFICATION);
		} else {
			GratificationEntity gratification = gratificationservice.searchGratificationActive(g.getIdgratification());
			if (gratification == null) {
				arg1.rejectValue(IDGRATIFICATION, "gratificationsproblem");
				model.addObject(Constants.MACHINES, machineservice.searchMachinesOrder());
				model.addObject(GRATIFICATION, g);
				model.setViewName(VIEWNEWGRATIFICATION);
			} else {
				gratification.setMachine(g.getMachine());
				model.addObject(ConstantsJsp.DAILY, gratificationservice.save(gratification, user));
				model.setViewName(ConstantsJsp.DAILY);
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/registernumber")
	public ModelAndView registerNumber() {
		ModelAndView model = new ModelAndView(VIEWREGISTERGRATIFICATION);
		model.addObject(GRATIFICATION, new GratificationEntity());
		return model;
	}

	@RequestMapping(value = "/employee/registergratification")
	public ModelAndView registerGratification(@ModelAttribute(GRATIFICATION) GratificationEntity g, BindingResult arg1,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView(ConstantsJsp.VIEWSUCCESSEMPLOYEE);
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
		gvalidator.validate(g, arg1);
		if (arg1.hasErrors()) {
			model.addObject(GRATIFICATION, g);
			model.setViewName(VIEWREGISTERGRATIFICATION);
		} else {
			File file = new File(path.concat("ticket.pdf"));
			gratificationservice.registerNumberGratification(g, user, path);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=ticket.pdf");
			try (InputStream inputStream = new FileInputStream(file)) {
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				model.setViewName(VIEWREGISTERGRATIFICATION);
				arg1.rejectValue(ConstantsJsp.CLIENT, "notopenfile");
			}
		}
		return model;
	}

	@RequestMapping(value = "/employee/lastgratifications")
	public ModelAndView lastgratifications() {
		ModelAndView model = new ModelAndView("lastgratifications");
		model.addObject(Constants.GRATIFICATIONS, gratificationservice.lastNumGratifications());
		return model;
	}
}
