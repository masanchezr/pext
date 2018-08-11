package com.gu.dbaccess.test;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.repositories.TakingsRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class TakingsRepositoryTest {

	@Autowired
	private TakingsRepository takingsrepository;

	@Test
	public void findFirstTest() {
		Date date = takingsrepository.findFirstByOrderByIdtakeDesc().getTakedate();
		System.out.println(date);
	}
}
