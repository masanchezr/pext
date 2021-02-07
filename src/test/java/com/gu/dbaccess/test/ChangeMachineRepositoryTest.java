package com.gu.dbaccess.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.repositories.ChangeMachineRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
class ChangeMachineRepositoryTest {

	@Autowired
	private ChangeMachineRepository changemachinerepository;

	@Test
	void findFirstByOrderByCreationdateDescTest() {
		Assertions.assertNotNull(changemachinerepository.findFirstByOrderByCreationdateDesc());
	}

	@Test
	void sumByCreationdateBetweenAndAwardTest() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date from = sdf.parse("2020-01-01 09:00:00");
		Assertions.assertNotNull(changemachinerepository.sumByCreationdateBetweenAndAward(from, new Date()));
	}

}
