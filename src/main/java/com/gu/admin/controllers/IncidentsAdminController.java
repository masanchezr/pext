package com.gu.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gu.dbaccess.entities.IncidentEntity;
import com.gu.services.incidents.IncidentService;
import com.gu.util.constants.ConstantsJsp;

@Controller
public class IncidentsAdminController {

	@Autowired
	private IncidentService incidentService;

	@RequestMapping(value = "/admin/allincidents")
	public ModelAndView allincidents() {
		ModelAndView model = new ModelAndView("allincidents");
		model.addObject("incidents", incidentService.searchAllIncidents());
		model.addObject(ConstantsJsp.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@RequestMapping(value = "/admin/pendingissues")
	public ModelAndView pendingissues() {
		ModelAndView model = new ModelAndView("allincidents");
		model.addObject("incidents", incidentService.searchPending());
		model.addObject(ConstantsJsp.FORMINCIDENT, new IncidentEntity());
		return model;
	}

	@RequestMapping(value = "/admin/searchincident")
	public ModelAndView searchIncident(@ModelAttribute(ConstantsJsp.FORMINCIDENT) IncidentEntity incident) {
		ModelAndView model = new ModelAndView("updateincident");
		model.addObject(ConstantsJsp.FORMINCIDENT, incidentService.searchIncident(incident));
		return model;
	}

	@RequestMapping(value = "/admin/resolvedincident")
	public ModelAndView resolvedIncident(@ModelAttribute(ConstantsJsp.FORMINCIDENT) IncidentEntity incident) {
		if (incident != null && incident.getIdincident() != null) {
			incidentService.resolve(incident);
		}
		return pendingissues();
	}
}
