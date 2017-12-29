package com.gu.services.incidents;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.IncidentEntity;
import com.gu.dbaccess.repositories.IncidentRepository;
import com.gu.services.mails.MailService;

public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	public void save(IncidentEntity incident) {
		MailService mailService;
		incident.setCreationdate(new Date());
		incident.setState(Boolean.FALSE);
		incident = incidentRepository.save(incident);
		mailService = new MailService(incident.getDescription(), null, "NUEVA INCIDENCIA GOLDEN USERA");
		mailService.start();
	}

	public void resolve(IncidentEntity incident) {
		IncidentEntity entity = incidentRepository.findById(incident.getIdincident()).get();
		entity.setDescription(incident.getDescription());
		entity.setState(Boolean.TRUE);
		incidentRepository.save(entity);
	}

	public List<IncidentEntity> searchAllIncidents() {
		List<IncidentEntity> incidents = mapper(incidentRepository.findAll());
		return incidents;
	}

	public List<IncidentEntity> searchPending() {
		return mapper(incidentRepository.findByState(Boolean.FALSE));
	}

	private List<IncidentEntity> mapper(Iterable<IncidentEntity> entities) {
		List<IncidentEntity> incidents = new ArrayList<IncidentEntity>();
		Iterator<IncidentEntity> ientities = entities.iterator();
		while (ientities.hasNext()) {
			incidents.add(ientities.next());
		}
		return incidents;
	}

	public IncidentEntity searchIncident(IncidentEntity incident) {
		return incidentRepository.findById(incident.getIdincident()).get();
	}
}
