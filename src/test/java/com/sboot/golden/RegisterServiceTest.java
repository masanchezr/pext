package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.services.register.RegisterService;

@SpringBootTest
class RegisterServiceTest {

	@Autowired
	private RegisterService registerService;

	@Test
	void findByDatesTest() {
		String from = "13/05/2019";
		String until = "15/05/2019";
		assertNotNull(registerService.findByDates(from, until));
	}

}
