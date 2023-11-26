package com.atmj.gsboot.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.services.users.User;
import com.atmj.gsboot.services.users.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	void newUserTest() {
		User user = new User();
		user.setEnabled(true);
		user.setAlias("Prueba11");
		user.setName("Prueba11");
		user.setPassword("ROVIN");
		user.setRole("USER");
		user.setUsername("prueba11");
		if (userService.findUser(user.getUsername()) == null) {
			userService.newUser(user);
		}
	}
}
