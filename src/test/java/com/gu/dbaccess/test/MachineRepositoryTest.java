package com.gu.dbaccess.test;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.dbaccess.entities.MachineEntity;
import com.gu.dbaccess.repositories.MachinesRepository;

@ExtendWith(SpringExtension.class)
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
