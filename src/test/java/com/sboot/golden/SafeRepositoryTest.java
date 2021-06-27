package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.dbaccess.entities.SafeEntity;
import com.sboot.golden.dbaccess.repositories.SafeRepository;
import com.sboot.golden.util.date.DateUtil;

@SpringBootTest
class SafeRepositoryTest {

	@Autowired
	private SafeRepository saferepository;

	@Test
	void saveTest() {
		SafeEntity safe = new SafeEntity();
		safe.setCreationdate(new DateUtil().getNow());
		safe.setAmount(BigDecimal.valueOf(200));
		safe.setTotal(BigDecimal.valueOf(12000));
		assertNotNull(saferepository.save(safe));
	}

	@Test
	void findByFirstTest() {
		assertNotNull(saferepository.findFirstByOrderByIdsafeDesc());
	}
}
