package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.services.gratifications.GratificationService;

@SpringBootTest
class GratificationServiceTest {

	@Autowired
	private GratificationService gratificationservice;

	@Test
	void lastnumgratificationsTest() {
		assertNotNull(gratificationservice.lastNumGratifications());
	}
}
