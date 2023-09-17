package com.sboot.golden;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.services.metadata.MetadataService;

@SpringBootTest
public class MetadataServiceTest {

	@Autowired
	private MetadataService metadataservice;

	@Test
	public void getValueTest() {
		assertNotNull(metadataservice.getValue("datadir"));

	}
}
