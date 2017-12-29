package com.gu.dbaccess.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.repositories.ChangeMachineRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class ChangeMachineRepositoryTest {

	@Autowired
	private ChangeMachineRepository changemachinerepository;

	@Test
	public void findFirstByOrderByCreationdateDescTest() {
		changemachinerepository.findFirstByOrderByCreationdateDesc();
	}

}
