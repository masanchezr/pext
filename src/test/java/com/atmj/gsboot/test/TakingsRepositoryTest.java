package com.atmj.gsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.dbaccess.entities.TakeEntity;
import com.atmj.gsboot.dbaccess.repositories.TakingsRepository;

@SpringBootTest
class TakingsRepositoryTest {

	@Autowired
	private TakingsRepository takingsrepository;

	@Test
	void findFirstTest() {
		assertNotNull(takingsrepository.findFirstByOrderByIdtakeDesc().getTakedate());
	}

	@Test
	void findAllOrderByIdtakeTest() {
		Iterable<TakeEntity> takings = takingsrepository.findByOrderByIdtakeDesc();
		Iterator<TakeEntity> iterator = takings.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getTakedate());
		}
	}
}
