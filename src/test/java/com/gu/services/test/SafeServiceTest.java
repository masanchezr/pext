package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.SafeEntity;
import com.gu.services.safe.SafeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class SafeServiceTest {

	@Autowired
	private SafeService safeService;

	@Test
	public void entryMachine() {
		SafeEntity safe = new SafeEntity();
		safe.setAmount(new BigDecimal(3000));
		safeService.saveSub(safe);
	}
}
