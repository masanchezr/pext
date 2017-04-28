package com.bar.employee.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bar.dbaccess.entities.IncidentEntity;
import com.bar.services.incidents.IncidentService;
import com.bar.validators.IncidentValidator;

@Controller
public class IncidentsController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private IncidentValidator incidentValidator;

	@RequestMapping(value = "/employee/newincident")
	public ModelAndView newIncident() {
		ModelAndView model = new ModelAndView("newincident");
		model.addObject("incident", new IncidentEntity());
		return model;
	}

	@RequestMapping(value = "/employee/saveincident")
	public ModelAndView saveIncident(@ModelAttribute("incident") IncidentEntity incident, BindingResult result) {
		ModelAndView model = new ModelAndView();
		incidentValidator.validate(incident, result);
		if (result.hasErrors()) {
			model.setViewName("newincident");
		} else {
			model.setViewName("saveincident");
			incidentService.save(incident);
		}
		model.addObject("incident", incident);
		return model;
	}

	@RequestMapping(value = "/employee/myincidents")
	public ModelAndView myincidents() {
		ModelAndView model = new ModelAndView("myincidents");
		List<IncidentEntity> incidents = incidentService.searchPending();
		model.addObject("incidents", incidents);
		return model;
	}
}
