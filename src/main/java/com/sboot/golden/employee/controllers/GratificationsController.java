package com.sboot.golden.employee.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.GratificationEntity;
import com.sboot.golden.employee.forms.Gratification;
import com.sboot.golden.employee.validators.GratificationsValidator;
import com.sboot.golden.services.gratifications.GratificationService;
import com.sboot.golden.services.machines.MachineService;
import com.sboot.golden.util.constants.Constants;
import com.sboot.golden.util.constants.ConstantsViews;

@Controller
public class GratificationsController {

	@Autowired
	private GratificationService gratificationservice;

	@Autowired
	private MachineService machineservice;

	@Autowired
	private Mapper mapper;

	private static final String GRATIFICATION = "gratification";
	private static final String VIEWNEWGRATIFICATION = "employee/gratifications/gratification";
	private static final String VIEWREGISTERGRATIFICATION = "employee/gratifications/registergratification";
	private static final String IDGRATIFICATION = "idgratification";

	@GetMapping("/employee/newgratification")
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
	@PostMapping("/employee/savegratification")
	public ModelAndView save(@ModelAttribute(GRATIFICATION) Gratification g, BindingResult arg1) {
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
				model.addObject(ConstantsViews.DAILY, gratificationservice.save(gratification, user));
				model.setViewName("employee/daily/daily");
			}
		}
		return model;
	}

	@GetMapping("/employee/registernumber")
	public ModelAndView registerNumber() {
		ModelAndView model = new ModelAndView(VIEWREGISTERGRATIFICATION);
		model.addObject(GRATIFICATION, new GratificationEntity());
		return model;
	}

	@PostMapping("/employee/registergratification")
	public void registerGratification(
			@Validated(GratificationsValidator.class) @ModelAttribute(GRATIFICATION) Gratification g,
			BindingResult arg1, HttpServletResponse response) {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		String path = System.getenv(Constants.OPENSHIFT_DATA_DIR);
		if (!arg1.hasErrors()) {
			File file = new File(path.concat("ticket.pdf"));
			gratificationservice.registerNumberGratification(mapper.map(g, GratificationEntity.class), user, path);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=ticket.pdf");
			try (InputStream inputStream = new FileInputStream(file)) {
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
			} catch (IOException e) {
				arg1.rejectValue(ConstantsViews.CLIENT, "notopenfile");
			}
		}
	}

	@GetMapping("/employee/lastgratifications")
	public ModelAndView lastgratifications() {
		ModelAndView model = new ModelAndView("employee/gratifications/lastgratifications");
		model.addObject(Constants.GRATIFICATIONS, gratificationservice.lastNumGratifications());
		return model;
	}
}
