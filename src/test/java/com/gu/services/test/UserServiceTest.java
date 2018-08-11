package com.gu.services.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.gu.services.users.User;
import com.gu.services.users.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class UserServiceTest {

	@Autowired
	private UserService userservice;

	@Test
	public void updatepasswordTest() {
		User user = new User();
		user.setUsername("masanchez");
		user.setPassword("josetequiero");
		userservice.updatePassword(user);
	}

}
