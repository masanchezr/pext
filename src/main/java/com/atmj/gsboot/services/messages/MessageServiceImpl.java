package com.atmj.gsboot.services.messages;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.MessageEntity;
import com.atmj.gsboot.dbaccess.repositories.MessagesRepository;
import com.atmj.gsboot.util.date.DateUtil;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessagesRepository messagesrepository;

	@Autowired
	private ModelMapper mapper;

	public List<MessageEntity> getMessagesActiveNow() {
		Date now = new DateUtil().getNow();
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
		entity.setCreationdate(new DateUtil().getNow());
		messagesrepository.save(entity);
	}

	public Message findById(Long idmessage) {
		return mapper.map(messagesrepository.findById(idmessage).orElse(null), Message.class);
	}
}
