package com.sboot.golden.admin.controllers;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sboot.golden.dbaccess.entities.IncidentEntity;
import com.sboot.golden.forms.Incident;
import com.sboot.golden.services.incidents.IncidentService;
import com.sboot.golden.util.constants.ConstantsViews;

@Controller
public class IncidentsAdminController {

	@Autowired
	private IncidentService incidentService;

	@Autowired
	private Mapper mapper;

	@GetMapping("/admin/allincidents")
	public ModelAndView allincidents() {
		ModelAndView model = new ModelAndView("admin/incidents/allincidents");
		model.addObject("incidents", incidentService.searchAllIncidents());
		model.addObject(ConstantsViews.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@GetMapping("/admin/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("admin/incidents/allincidents");
		model.addObject("incidents", incidentService.searchPending());
		model.addObject(ConstantsViews.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@PostMapping("/admin/searchincident")
	public ModelAndView searchIncident(@ModelAttribute(ConstantsViews.FORMINCIDENT) Incident incident) {
		ModelAndView model = new ModelAndView("admin/incidents/updateincident");
		model.addObject(ConstantsViews.FORMINCIDENT,
				mapper.map(incidentService.searchIncident(mapper.map(incident, IncidentEntity.class)), Incident.class));
		return model;
	}

	@PostMapping("/admin/resolvedincident")
	public ModelAndView resolvedIncident(@ModelAttribute(ConstantsViews.FORMINCIDENT) Incident incident) {
		if (incident != null && incident.getIdincident() != null) {
			incidentService.resolve(mapper.map(incident, IncidentEntity.class));
		}
		return pendingissues();
	}
}
