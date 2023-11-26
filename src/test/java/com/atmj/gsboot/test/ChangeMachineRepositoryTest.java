package com.atmj.gsboot.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.dbaccess.repositories.ChangeMachineRepository;

@SpringBootTest
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
		List<Long> awards = new ArrayList<>();
		awards.add(1L);
		awards.add(4L);
		Assertions.assertNotNull(changemachinerepository.sumByCreationdateBetweenAndAward(from, new Date(), awards));
	}

}
