package com.atmj.gsboot.employee.controllers;

import java.util.List;

import jakarta.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.atmj.gsboot.dbaccess.entities.IncidentEntity;
import com.atmj.gsboot.forms.Incident;
import com.atmj.gsboot.services.incidents.IncidentService;
import com.atmj.gsboot.util.constants.ConstantsViews;

@Controller
public class IncidentsController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private ModelMapper mapper;

	@GetMapping("/employee/newincident")
	public ModelAndView newIncident() {
		ModelAndView model = new ModelAndView("employee/incidents/newincident");
		model.addObject(ConstantsViews.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@PostMapping("/employee/saveincident")
	public ModelAndView saveIncident(@Valid Incident incident, BindingResult result) {
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
