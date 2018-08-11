package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.services.operations.OperationService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class OperationServiceTest {

	@Autowired
	private OperationService operationservice;

	@Test
	public void updateOperationTest() {
		OperationEntity operation = operationservice.findById(27L);
		operation.setAmount(new BigDecimal(100));
		operationservice.update(operation);
	}

}
