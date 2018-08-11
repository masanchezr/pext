package com.gu.services.test;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.services.gratifications.GratificationService;

@ExtendWith(SpringExtension.class)
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

	@Test
	public void lastnumgratificationsTest() {
		List<GratificationEntity> gratifications = gratificationservice.lastNumGratifications();
		Iterator<GratificationEntity> igratifications = gratifications.iterator();
		while (igratifications.hasNext()) {
			System.out.println(igratifications.next().getIdgratification());
		}
	}
}
