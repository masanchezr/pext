package com.gu.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.MessageEntity;

public interface MessagesRepository extends CrudRepository<MessageEntity, Long> {

	public List<MessageEntity> findByActiveTrueAndDatefromBeforeAndDateuntilAfter(
			@Temporal(TemporalType.TIME) Date from, @Temporal(TemporalType.TIME) Date until);

}
