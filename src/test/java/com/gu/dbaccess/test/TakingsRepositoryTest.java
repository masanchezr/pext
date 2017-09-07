package com.gu.dbaccess.test;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.repositories.TakingsRepository;

@RunWith(SpringJUnit4ClassRunner.class)
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
