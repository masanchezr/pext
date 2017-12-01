package com.gu.services.messages;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.MessageEntity;
import com.gu.dbaccess.repositories.MessagesRepository;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagesRepository messagesrepository;

	public List<MessageEntity> getMessagesActiveNow() {
		Date now = new Date();
		List<MessageEntity> messages = messagesrepository.findByActiveTrueAndDatefromBeforeAndDateuntilAfter(now, now);
		return messages;
	}

	public Iterable<MessageEntity> getAllMessages() {
		return messagesrepository.findAll();
	}

	public void save(MessageEntity message) {
		messagesrepository.save(message);
	}

	public MessageEntity findById(Long idmessage) {
		return messagesrepository.findOne(idmessage);
	}
}
