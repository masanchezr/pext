package com.gu.services.messages;

import java.util.List;

import com.gu.dbaccess.entities.MessageEntity;

public interface MessageService {

	public List<MessageEntity> getMessages();

}
