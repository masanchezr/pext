package com.bar.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bar.dbaccess.entities.IncidentEntity;
import com.bar.services.incidents.IncidentService;

@Controller
public class IncidentsAdminController {

	@Autowired
	private IncidentService incidentService;

	@RequestMapping(value = "/admin/allincidents")
	public ModelAndView allincidents() {
		ModelAndView model = new ModelAndView("allincidents");
		model.addObject("incidents", incidentService.searchAllIncidents());
		model.addObject("incident", new IncidentEntity());
		return model;
	}

	@RequestMapping(value = "/admin/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("allincidents");
		model.addObject("incidents", incidentService.searchPending());
		model.addObject("incident", new IncidentEntity());
		return model;
	}

	@RequestMapping(value = "/admin/searchincident")
	public ModelAndView searchIncident(@ModelAttribute("incident") IncidentEntity incident) {
		ModelAndView model = new ModelAndView("updateincident");
		model.addObject("incident", incidentService.searchIncident(incident));
		return model;
	}

	@RequestMapping(value = "/admin/resolvedincident")
	public ModelAndView resolvedIncident(@ModelAttribute("incident") IncidentEntity incident) {
		ModelAndView model = new ModelAndView();
		if (incident != null && incident.getIdincident() != null) {
			model.setViewName("successadmin");
			incidentService.resolve(incident);
		} else {
			model.addObject("incidents", incidentService.searchAllIncidents());
			model.addObject("incident", new IncidentEntity());
			model.setViewName("allincidents");
		}
		return model;
	}
}
