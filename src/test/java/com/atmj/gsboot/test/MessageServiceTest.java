package com.atmj.gsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.services.messages.MessageService;

@SpringBootTest
class MessageServiceTest {

	@Autowired
	private MessageService messageservice;

	@Test
	void getMessagesTest() {
		assertNotNull(messageservice.getMessagesActiveNow());
	}
}
