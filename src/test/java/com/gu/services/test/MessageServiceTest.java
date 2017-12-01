package com.gu.services.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.MessageEntity;
import com.gu.services.messages.MessageService;

@RunWith(SpringJUnit4ClassRunner.class)
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
