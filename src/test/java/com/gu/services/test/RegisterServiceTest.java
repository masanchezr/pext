package com.gu.services.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.services.register.RegisterService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class RegisterServiceTest {

	@Autowired
	private RegisterService registerService;

	@Test
	public void findByDatesTest() {
		String from = "13/05/2019";
		String until = "15/05/2019";
		registerService.findByDates(from, until);

	}

}
