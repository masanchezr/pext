package com.sboot.golden.employee.controllers;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.IncidentEntity;
import com.sboot.golden.employee.validators.IncidentValidator;
import com.sboot.golden.forms.Incident;
import com.sboot.golden.services.incidents.IncidentService;
import com.sboot.golden.util.constants.ConstantsViews;

@Controller
public class IncidentsController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private Mapper mapper;

	@GetMapping("/employee/newincident")
	public ModelAndView newIncident() {
		ModelAndView model = new ModelAndView("employee/incidents/newincident");
		model.addObject(ConstantsViews.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@PostMapping("/employee/saveincident")
	public ModelAndView saveIncident(
			@Validated(IncidentValidator.class) @ModelAttribute(ConstantsViews.FORMINCIDENT) Incident incident,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		if (result.hasErrors()) {
			model.setViewName("employee/incidents/newincident");
		} else {
			model.setViewName("employee/incidents/resultsaveincident");
			incidentService.save(mapper.map(incident, IncidentEntity.class));
		}
		model.addObject(ConstantsViews.FORMINCIDENT, incident);
		return model;
	}

	@GetMapping("/employee/myincidents")
	public ModelAndView myincidents() {
		ModelAndView model = new ModelAndView("employee/incidents/myincidents");
		List<IncidentEntity> incidents = incidentService.searchPending();
		model.addObject("incidents", incidents);
		return model;
	}
}
