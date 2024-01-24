package com.atmj.gsboot.services.takings;

import java.util.Date;

import com.atmj.gsboot.dbaccess.entities.TakeEntity;

public interface TakeService {

	Iterable<TakeEntity> getAllTakings();

	Date getFrom(Long id);

	TakeEntity findById(Long id);

}
