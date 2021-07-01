package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.services.entrymoney.EntryMoneyService;

@SpringBootTest
class EntryMoneyServiceTest {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Test
	void searchByDatesTest() {
		assertNotNull(entryMoneyService.searchByDates("02-05-2018"));
	}
}
