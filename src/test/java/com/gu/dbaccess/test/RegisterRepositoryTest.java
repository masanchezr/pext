package com.gu.dbaccess.test;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.RegisterEntity;
import com.gu.dbaccess.repositories.RegisterRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class RegisterRepositoryTest {

	@Autowired
	private RegisterRepository registerRepository;

	@Test
	public void findByDateTest() {
		List<RegisterEntity> r = registerRepository.selectByDate(new Date());
		r.toString();
	}

}
