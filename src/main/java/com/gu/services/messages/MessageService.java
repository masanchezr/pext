package com.gu.services.messages;

import java.util.List;

import com.gu.dbaccess.entities.MessageEntity;

public interface MessageService {

	public List<MessageEntity> getMessagesActiveNow();

	public Iterable<MessageEntity> getAllMessages();

	public void save(Message message);

	public MessageEntity findOne(Long idmessage);

}
