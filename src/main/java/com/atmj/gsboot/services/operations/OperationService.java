package com.atmj.gsboot.services.operations;

import java.util.Map;

import com.atmj.gsboot.dbaccess.entities.OperationEntity;
import com.atmj.gsboot.dbaccess.entities.OperationNotAllowedEntity;
import com.atmj.gsboot.services.dailies.Daily;

public interface OperationService {

	public Daily save(OperationEntity operation);

	public Map<String, Object> findExpensesByMonth(String month);

	public OperationEntity findById(long id);

	public Daily update(OperationEntity operation);

	public void delete(OperationEntity operation);

	public OperationNotAllowedEntity getOperationNotAllowed(OperationEntity operation);

}
