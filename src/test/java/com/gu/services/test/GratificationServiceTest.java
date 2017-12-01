package com.gu.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.services.gratifications.GratificationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class GratificationServiceTest {

	@Autowired
	private GratificationService gratificationservice;

	@Test
	public void generateTicketTest() {
		GratificationEntity g = new GratificationEntity();
		g.setClient("chinoFeo");
		gratificationservice.registerNumberGratification(g, "masanchez", "F://");
	}
}
