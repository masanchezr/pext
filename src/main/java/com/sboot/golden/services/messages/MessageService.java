package com.sboot.golden.services.messages;

import java.util.List;

import com.sboot.golden.dbaccess.entities.MessageEntity;

public interface MessageService {

	public List<MessageEntity> getMessagesActiveNow();

	public List<Message> getAllMessages();

	public void save(Message message);

	public Message findById(Long idmessage);

}
