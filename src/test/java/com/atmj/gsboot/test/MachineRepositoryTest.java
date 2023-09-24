package com.atmj.gsboot.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.dbaccess.repositories.MachinesRepository;

@SpringBootTest
class MachineRepositoryTest {

	@Autowired
	private MachinesRepository machinerepository;

	@Test
	void findByOrderByOrderTest() {
		assertNotNull(machinerepository.findByOrderByOrdermachine());
	}

}
