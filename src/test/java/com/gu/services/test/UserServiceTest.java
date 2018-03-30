package com.gu.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.services.users.User;
import com.gu.services.users.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
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
