package com.gu.dbaccess.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.DailyEntity;
import com.gu.dbaccess.repositories.DailyRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class DailyRepositoryTest {

	@Autowired
	private DailyRepository dailyRepository;

	@Test
	public void findFirstByOrderByDailyDateTest() {
		DailyEntity daily = dailyRepository.findFirstByOrderByDailydateDesc();
		System.out.println(daily.getDailydate());
	}

}
