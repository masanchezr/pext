package com.atmj.gsboot.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmj.gsboot.dbaccess.entities.MessageEntity;
import com.atmj.gsboot.services.messages.Message;
import com.atmj.gsboot.util.date.DateUtil;

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