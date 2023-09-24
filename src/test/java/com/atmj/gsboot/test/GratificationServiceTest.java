package com.atmj.gsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.dbaccess.entities.GratificationEntity;
import com.atmj.gsboot.services.gratifications.GratificationService;

@SpringBootTest
class GratificationServiceTest {

	@Autowired
	private GratificationService gratificationservice;

	@Test
	void lastnumgratificationsTest() {
		assertNotNull(gratificationservice.lastNumGratifications());
	}

	@Test
	void registerNumberGratificationTest() {
		GratificationEntity entity = new GratificationEntity();
		entity.setClient("pruebas");
		entity.setCreationdate(new Date());
		gratificationservice.registerNumberGratification(entity, "masanchez", "/home/mangeles/");
	}
}
