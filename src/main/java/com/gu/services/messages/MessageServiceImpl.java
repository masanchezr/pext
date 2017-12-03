package com.gu.services.messages;

import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.MessageEntity;
import com.gu.dbaccess.repositories.MessagesRepository;

public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagesRepository messagesrepository;

	@Autowired
	private Mapper mapper;

	public List<MessageEntity> getMessagesActiveNow() {
		Date now = new Date();
		List<MessageEntity> messages = messagesrepository.findByActiveTrueAndDatefromBeforeAndDateuntilAfter(now, now);
		return messages;
	}

	public Iterable<MessageEntity> getAllMessages() {
		return messagesrepository.findAll();
	}

	public void save(Message message) {
		MessageEntity entity = mapper.map(message, MessageEntity.class);
		entity.setCreationdate(new Date());
		messagesrepository.save(entity);
	}

	public MessageEntity findById(Long idmessage) {
		return messagesrepository.findOne(idmessage);
	}
}
