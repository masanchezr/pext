package com.sboot.golden.services.incidents;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.IncidentEntity;
import com.sboot.golden.dbaccess.repositories.IncidentRepository;
import com.sboot.golden.services.mails.EmailService;
import com.sboot.golden.util.date.DateUtil;

@Service
public class IncidentServiceImpl implements IncidentService {

	@Autowired
	private IncidentRepository incidentRepository;

	@Autowired
	private EmailService emailService;

	public void save(IncidentEntity incident) {
		incident.setCreationdate(new DateUtil().getNow());
		incident.setState(Boolean.FALSE);
		incident = incidentRepository.save(incident);
		emailService.sendSimpleMessage("godomin1971@gmail.com", "NUEVA INCIDENCIA GOLDEN USERA",
				incident.getDescription());
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
