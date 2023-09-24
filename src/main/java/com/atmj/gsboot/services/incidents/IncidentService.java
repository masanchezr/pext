package com.atmj.gsboot.services.incidents;

import java.util.List;

import com.atmj.gsboot.dbaccess.entities.IncidentEntity;

public interface IncidentService {

	public void save(IncidentEntity incident);

	public List<IncidentEntity> searchAllIncidents();

	public void resolve(IncidentEntity incident);

	public IncidentEntity searchIncident(IncidentEntity incident);

	public List<IncidentEntity> searchPending();
}
