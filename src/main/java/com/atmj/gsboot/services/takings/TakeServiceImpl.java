package com.atmj.gsboot.services.takings;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.TakeEntity;
import com.atmj.gsboot.dbaccess.repositories.TakingsRepository;

@Service
public class TakeServiceImpl implements TakeService {

	@Autowired
	private TakingsRepository takingsRepository;

	@Override
	public Iterable<TakeEntity> getAllTakings() {
		return takingsRepository.findByOrderByIdtakeDesc();
	}

	@Override
	public Date getFrom(Long id) {
		Date from = null;
		Iterable<TakeEntity> itakings = takingsRepository.findByOrderByIdtakeDesc();
		Iterator<TakeEntity> iterator = itakings.iterator();
		while (iterator.hasNext() && from == null) {
			if (iterator.next().getIdtake().equals(id)) {
				from = iterator.next().getTakedate();
			}
		}
		return from;
	}

	@Override
	public TakeEntity findById(Long id) {
		return takingsRepository.findById(id).get();
	}

}
