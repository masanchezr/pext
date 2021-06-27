package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.dbaccess.repositories.TakingsRepository;

@SpringBootTest
class TakingsRepositoryTest {

	@Autowired
	private TakingsRepository takingsrepository;

	@Test
	void findFirstTest() {
		assertNotNull(takingsrepository.findFirstByOrderByIdtakeDesc().getTakedate());
	}
}
