package com.atmj.gsboot.services.messages;

import java.util.List;

import com.atmj.gsboot.dbaccess.entities.MessageEntity;

public interface MessageService {

	public List<MessageEntity> getMessagesActiveNow();

	public List<Message> getAllMessages();

	public void save(Message message);

	public Message findById(Long idmessage);

}
