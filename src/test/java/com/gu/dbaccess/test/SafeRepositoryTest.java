package com.gu.dbaccess.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.SafeEntity;
import com.gu.dbaccess.repositories.SafeRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class SafeRepositoryTest {

	@Autowired
	private SafeRepository saferepository;

	@Test
	public void saveTest() {
		SafeEntity safe = new SafeEntity();
		safe.setCreationdate(new Date());
		safe.setAmount(new BigDecimal(200));
		safe.setTotal(new BigDecimal(12000));
		saferepository.save(safe);
	}

	@Test
	public void findByFirstTest() {
		SafeEntity safe = saferepository.findFirstByOrderByIdsafeDesc();
		System.out.println(safe.getTotal());
	}
}
