package com.atmj.gsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.dbaccess.repositories.OperationsRepository;

@SpringBootTest
class OperationRepositoryTest {

	@Autowired
	private OperationsRepository operationRepository;

	@Test
	void findExpensesByMonthTest() {
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		assertNotNull(operationRepository.searchSumByMonth(from, until));
	}
}
