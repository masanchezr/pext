package com.sboot.golden.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sboot.golden.dbaccess.entities.MessageEntity;
import com.sboot.golden.services.messages.Message;
import com.sboot.golden.util.date.DateUtil;

@Component
public class MessageConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public MessageEntity convertToEntity(Message message) {
		MessageEntity entity = mapper.map(message, MessageEntity.class);
		entity.setDatefrom(DateUtil.getDate(message.getDatefrom()));
		entity.setDateuntil(DateUtil.getDate(message.getDateuntil()));
		return entity;
	}

}