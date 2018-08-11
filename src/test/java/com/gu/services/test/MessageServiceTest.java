package com.gu.services.test;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.MessageEntity;
import com.gu.services.messages.MessageService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class MessageServiceTest {

	@Autowired
	private MessageService messageservice;

	@Test
	public void getMessagesTest() {
		List<MessageEntity> messages = messageservice.getMessagesActiveNow();
		Iterator<MessageEntity> imessages = messages.iterator();
		while (imessages.hasNext()) {
			System.out.println(imessages.next().getMessage());
		}
	}
}
