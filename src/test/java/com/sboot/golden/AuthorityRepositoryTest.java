package com.sboot.golden;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sboot.golden.dbaccess.entities.AuthorityEntity;
import com.sboot.golden.dbaccess.repositories.AuthorityRepository;

@SpringBootTest
public class AuthorityRepositoryTest {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Test
	public void findByAuthorityTest() {
		AuthorityEntity entities = authorityRepository.findByAuthority("ROLE_USER");
		if (entities != null) {
			System.out.println(entities.getAuthority());
		}

	}
}
