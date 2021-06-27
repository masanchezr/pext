package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.dbaccess.entities.OperationEntity;
import com.sboot.golden.services.operations.OperationService;

@SpringBootTest
class OperationServiceTest {

	@Autowired
	private OperationService operationservice;

	@Test
	void updateOperationTest() {
		OperationEntity operation = operationservice.findById(27L);
		operation.setAmount(new BigDecimal(100));
		assertNotNull(operationservice.update(operation));
	}

}
