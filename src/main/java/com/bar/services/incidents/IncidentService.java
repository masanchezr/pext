package com.bar.services.incidents;

import java.util.List;

import com.bar.dbaccess.entities.IncidentEntity;

public interface IncidentService {

	public void save(IncidentEntity incident);

	public List<IncidentEntity> searchAllIncidents();

	public void resolve(IncidentEntity incident);

	public IncidentEntity searchIncident(IncidentEntity incident);

	public List<IncidentEntity> searchPending();
}
