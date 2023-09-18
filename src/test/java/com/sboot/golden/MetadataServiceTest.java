package com.sboot.golden;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.services.metadata.MetadataService;

@SpringBootTest
class MetadataServiceTest {

	@Autowired
	private MetadataService metadataservice;

	@Test
	void getValueTest() {
		assertThat(metadataservice.getValue("datadir"));

	}
}
