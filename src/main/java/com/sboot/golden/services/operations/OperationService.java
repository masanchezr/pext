package com.sboot.golden.services.operations;

import java.util.Map;

import com.sboot.golden.dbaccess.entities.OperationEntity;
import com.sboot.golden.dbaccess.entities.OperationNotAllowedEntity;
import com.sboot.golden.services.dailies.Daily;

public interface OperationService {

	public Daily save(OperationEntity operation);

	public Map<String, Object> findExpensesByMonth(String month);

	public OperationEntity findById(long id);

	public Daily update(OperationEntity operation);

	public void delete(OperationEntity operation);

	public OperationNotAllowedEntity getOperationNotAllowed(OperationEntity operation);

}
