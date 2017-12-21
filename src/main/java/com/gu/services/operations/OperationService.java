package com.gu.services.operations;

import java.util.List;
import java.util.Map;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.dbaccess.entities.OperationNotAllowedEntity;
import com.gu.services.dailies.Daily;

public interface OperationService {

	public Daily save(OperationEntity operation);

	public Map<String, Object> findExpensesByMonth(String month);

	public OperationEntity findOne(long id);

	public Daily update(OperationEntity operation);

	public void delete(OperationEntity operation);

	public List<OperationEntity> recharges(String month);

	public OperationNotAllowedEntity getOperationNotAllowed(OperationEntity operation);

}
