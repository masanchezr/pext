package com.gu.dbaccess.test;

import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.dbaccess.entities.MachineEntity;
import com.gu.dbaccess.repositories.MachinesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-db-context-test.xml" })
public class MachineRepositoryTest {

	@Autowired
	private MachinesRepository machinerepository;

	@Test
	public void findByOrderByOrderTest() {
		List<MachineEntity> machines = machinerepository.findByOrderByOrdermachine();
		Iterator<MachineEntity> imachines = machines.iterator();
		while (imachines.hasNext()) {
			System.out.println(imachines.next().getName());
		}
	}

}
