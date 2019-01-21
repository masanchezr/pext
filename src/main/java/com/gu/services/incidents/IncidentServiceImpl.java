package com.gu.services.incidents;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
		mailService = new MailService(incident.getDescription(), "godomin1971@gmail.com",
				"NUEVA INCIDENCIA GOLDEN USERA");
		mailService.start();
	}

	public void resolve(IncidentEntity incident) {
		Optional<IncidentEntity> oincident = incidentRepository.findById(incident.getIdincident());
		if (oincident.isPresent()) {
			IncidentEntity entity = oincident.get();
			entity.setDescription(incident.getDescription());
			entity.setState(Boolean.TRUE);
			incidentRepository.save(entity);
		}
	}

	public List<IncidentEntity> searchAllIncidents() {
		Iterable<IncidentEntity> entities = incidentRepository.findAll();
		return mapper(entities);
	}

	public List<IncidentEntity> searchPending() {
		return mapper(incidentRepository.findByState(Boolean.FALSE));
	}

	private List<IncidentEntity> mapper(Iterable<IncidentEntity> entities) {
		List<IncidentEntity> incidents = new ArrayList<>();
		Iterator<IncidentEntity> ientities = entities.iterator();
		while (ientities.hasNext()) {
			incidents.add(ientities.next());
		}
		return incidents;
	}

	public IncidentEntity searchIncident(IncidentEntity incident) {
		Optional<IncidentEntity> oincident = incidentRepository.findById(incident.getIdincident());
		if (oincident.isPresent()) {
			return oincident.get();
		} else {
			return null;
		}
	}
}
