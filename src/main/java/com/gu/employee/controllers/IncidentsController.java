package com.gu.employee.controllers;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.IncidentEntity;
import com.gu.employee.validators.IncidentValidator;
import com.gu.forms.Incident;
import com.gu.services.incidents.IncidentService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class IncidentsController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private IncidentValidator incidentValidator;

	@Autowired
	private Mapper mapper;

	@GetMapping("/employee/newincident")
	public ModelAndView newIncident() {
		ModelAndView model = new ModelAndView("employee/incidents/newincident");
		model.addObject(ConstantsJsp.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@PostMapping("/employee/saveincident")
	public ModelAndView saveIncident(@ModelAttribute(ConstantsJsp.FORMINCIDENT) Incident incident,
			BindingResult result) {
		ModelAndView model = new ModelAndView();
		incidentValidator.validate(incident, result);
		if (result.hasErrors()) {
			model.setViewName("employee/incidents/newincident");
		} else {
			model.setViewName("employee/incidents/resultsaveincident");
			incidentService.save(mapper.map(incident, IncidentEntity.class));
		}
		model.addObject(ConstantsJsp.FORMINCIDENT, incident);
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
