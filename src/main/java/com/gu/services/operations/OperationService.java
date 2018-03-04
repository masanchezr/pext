package com.gu.services.operations;

import java.util.Map;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.dbaccess.entities.OperationNotAllowedEntity;
import com.gu.services.dailies.Daily;

public interface OperationService {

	public Daily save(OperationEntity operation);

	public Map<String, Object> findExpensesByMonth(String month);

	public OperationEntity findById(long id);

	public Daily update(OperationEntity operation);

	public void delete(OperationEntity operation);

	public OperationNotAllowedEntity getOperationNotAllowed(OperationEntity operation);

}
