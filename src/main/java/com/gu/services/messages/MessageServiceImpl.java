package com.gu.services.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
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
		return messagesrepository.findByActiveTrueAndDatefromBeforeAndDateuntilAfter(now, now);
	}

	public List<Message> getAllMessages() {
		Iterable<MessageEntity> entities = messagesrepository.findAll();
		Iterator<MessageEntity> imessages = entities.iterator();
		List<Message> messages = new ArrayList<>();
		while (imessages.hasNext()) {
			messages.add(mapper.map(imessages.next(), Message.class));
		}
		return messages;
	}

	public void save(Message message) {
		MessageEntity entity = mapper.map(message, MessageEntity.class);
		entity.setCreationdate(new Date());
		messagesrepository.save(entity);
	}

	public MessageEntity findById(Long idmessage) {
		return messagesrepository.findById(idmessage).orElse(null);
	}
}
