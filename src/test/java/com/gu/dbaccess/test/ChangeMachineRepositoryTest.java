package com.gu.dbaccess.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.repositories.ChangeMachineRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class ChangeMachineRepositoryTest {

	@Autowired
	private ChangeMachineRepository changemachinerepository;

	@Test
	public void findFirstByOrderByCreationdateDescTest() {
		changemachinerepository.findFirstByOrderByCreationdateDesc();
	}

}
