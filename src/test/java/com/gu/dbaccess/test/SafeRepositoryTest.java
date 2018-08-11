package com.gu.dbaccess.test;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.SafeEntity;
import com.gu.dbaccess.repositories.SafeRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class SafeRepositoryTest {

	@Autowired
	private SafeRepository saferepository;

	@Test
	public void saveTest() {
		SafeEntity safe = new SafeEntity();
		safe.setCreationdate(new Date());
		safe.setAmount(BigDecimal.valueOf(200));
		safe.setTotal(BigDecimal.valueOf(12000));
		saferepository.save(safe);
	}

	@Test
	public void findByFirstTest() {
		SafeEntity safe = saferepository.findFirstByOrderByIdsafeDesc();
		System.out.println(safe.getTotal());
	}
}
