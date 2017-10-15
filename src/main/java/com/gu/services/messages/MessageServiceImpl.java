package com.gu.services.messages;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.MessageEntity;
import com.gu.dbaccess.repositories.MessagesRepository;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagesRepository messagesrepository;

	public List<MessageEntity> getMessages() {
		Date now = new Date();
		List<MessageEntity> messages = messagesrepository.findByActiveTrueAndDatefromBeforeAndDateuntilAfter(now, now);
		return messages;
	}

}
