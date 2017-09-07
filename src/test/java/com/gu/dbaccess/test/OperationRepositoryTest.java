package com.gu.dbaccess.test;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.repositories.OperationsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class OperationRepositoryTest {

	@Autowired
	private OperationsRepository operationRepository;

	@Test
	public void findExpensesByMonthTest() {
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.YEAR, 2017);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		List<Object[]> list = operationRepository.searchSumByMonth(from, until);
		Iterator<Object[]> ilist = list.iterator();
	}
}
