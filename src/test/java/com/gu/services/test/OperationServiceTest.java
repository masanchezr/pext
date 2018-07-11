package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.OperationEntity;
import com.gu.services.operations.OperationService;

@RunWith(SpringJUnit4ClassRunner.class)
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
